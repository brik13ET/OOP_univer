/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threads;

/**
 *
 * @author user0
 */
public class TransportSynchronizer
{
    private Object o;
    private Thread[] threads;
    private Runnable[] runs;
    private Runnable wanted;
    
    public TransportSynchronizer(Object res)
    {
        o = res;
    }
    
    public void start(Runnable ... threads)
    {
        this.runs = threads;
        this.threads = new Thread[threads.length];
        for (int i = 0; i < threads.length; i++)
        {
            this.threads[i] = new Thread(this.runs[i]);
        }
        wanted = threads[0];
        for (int i = 0; i < threads.length; i++)
        {
            this.threads[i].start();
        }
    }
    
    public Object WaitForQueue(Runnable r)
    {
        while (!wanted.equals(r));
        return o;
    }
    
    public void LeaveQueue(Runnable r)
    {
        for (int i = 0; i < runs.length; i++)
        {
            if (r.equals(runs[i]))
            {
                i = (i + 1) % runs.length;
                wanted = runs[i];
                return;
            }
        }
    }
}
