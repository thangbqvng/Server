/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.Server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer.Args;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
/**
 *
 * @author thangbq
 */
public class Server {

    private static ArrayHandler       handler;
    private static array.Processor    processor;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try{
           handler = new ArrayHandler();
           processor = new array.Processor(handler);
           
           Runnable simple = new Runnable(){
               public void run(){
                   simple(processor);
               }
           };
           new Thread(simple).start();
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
    }
    
    private static void simple(array.Processor processor){
        try {
            TServerTransport serverTransport = new TServerSocket(8000);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor).maxWorkerThreads(2).minWorkerThreads(1));
            System.out.println("Starting simple server...");
            server.serve();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}       

