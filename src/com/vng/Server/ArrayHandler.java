/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.Server;
/**
 *
 * @author thangbq
 */

import java.util.List;
import java.util.Spliterator;
import java.util.Vector;
public class ArrayHandler implements array.Iface {
    List<Integer> rs = new Vector<Integer>();
    @Override
    public void ping() throws org.apache.thrift.TException{
        
    }
    @Override
    public Result doAct(Type type, java.util.List<java.lang.Integer> agr) throws org.apache.thrift.TException{
        Result res = new Result();
        switch(type){
            case PUSH:
                rs.addAll(agr);
                res.error = 0;
                res.result = rs;
                return res;
            case POP:
                rs.remove(0);
                res.error = 1;
                res.result = rs;
                return res;
            case GET:
                if(agr.size()>1)
                {
                    res.error = -100; //dư số lượng tham số
                    res.result=null;
                    return res;
                }
                if(agr.size()<1)
                {
                    res.error = -98; //thiếu tham số
                    res.result=null;
                    return res;
                }
                res.result.get(agr.get(0));
                res.error = 2;
                res.result = rs;
                return res;
            case SHOW:
                res.error = 3;
                res.result=null;
                return res;
            case GET2:
                if(agr.size()>2)
                {
                    res.error = -100; //dư số lượng tham số
                    res.result=null;
                    return res;
                }
                if(agr.size()<2)
                {
                    res.error = -98; //thiếu tham số
                    res.result=null;
                    return res;
                }
                if(agr.get(0) < agr.get(1))
                {
                    res.error = -99; //sai tham số
                    res.result=null;
                    return res;
                }
                res.error = 4;
                for(int i = agr.get(0); i<= agr.get(1);i++){
                    res.result.add(rs.get(i));
                }
                return res;
            case TOP:
                if(agr.size()>1)
                {
                    res.error = -100; //dư số lượng tham số
                    res.result=null;
                    return res;
                }
                if(agr.size()<1)
                {
                    res.error = -98; //thiếu tham số
                    res.result=null;
                    return res;
                }
                
                res.error = 5;
                for(int i = rs.size() - 1 - agr.get(0); i< agr.size();i++){
                    res.result.add(rs.get(i));
                }
                return res;
        }
        return null;
    }
}
