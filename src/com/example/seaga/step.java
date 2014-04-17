/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author mohammed
 */
package com.example.seaga;
import java.io.FileWriter;
public class step {
    public state[] states=new state[4*24];
    public int state_num;
    public int max_state_num;
    public int best_state_result;
    public void print_to_file(FileWriter target,int depth){
        int i=0;
        try {target.write("oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");} catch (Exception e) {}
                for(i=0;i<state_num;i++)
                    states[i].print_to_file(target,depth,i);
    }
    public void step(){
        
    }
    public step(){
        int i;
        for(i=0;i<4*24;i++)
            states[i]=new state();
    }
}
