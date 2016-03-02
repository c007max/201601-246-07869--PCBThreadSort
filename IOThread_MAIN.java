import java.util.LinkedList;
import java.util.Iterator;
import java.util.Random;
import java.util.Collections ;

public class IOThread_MAIN
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException
	{
		int nodes_T	= 20 ;
		Random r1 = new Random();
		PCB	pcbRunning = null ;
		
		LinkedList<PCB> QReady	= new LinkedList<PCB>();
		
		for (int ii=0; ii<nodes_T; ii++)
		{
			if (((r1.nextInt(100)+1) % 5)==0)
			{
				pcbRunning	= new PCB() ;
				QReady.add(pcbRunning) ;
				
				Thread iop	= new Thread(new IOProcess(Integer.toString(ii)
						,pcbRunning
						,QReady
						));
				
				iop.start();
				
				System.out.printf("***\tmain: thread started %d %s %d %s\t***\n"
						,ii
						,iop.getName()
						,iop.getId()
						,iop.getState()
						);
			}
		}
		
		System.out.printf("***\tmain: threads still running: %d\t***\n"
				,Thread.activeCount()-1
				);
		
		Collections.sort(QReady);
		
		for (PCB loopI : QReady)
			System.out.printf("***\tmain: %s\t***\n"	,loopI.showPCB()) ;

		System.out.printf("@@@\tdone\t@@@\n");
	}
}
