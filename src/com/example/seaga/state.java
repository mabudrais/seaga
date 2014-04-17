/*
 * To change this template, choose Tools | Templates
 * and or.
 */

package com.example.seaga;
import java.io.FileWriter;

/**
 *
 * @author mohammed
 */
public class state {
    // sqr [0] [0] meaning the upper left peace
    public int[][] sqr=new int[7][7];
    public int state_result=0;// number of com peace-number of app peace
    public void copy(state a){
        int x,y;
        for(x=0;x<7;x++){
            for(y=0;y<7;y++)
                sqr[x][y]=a.sqr[x][y];
        }
        state_result=a.state_result;
    }
    public void print_to_file(FileWriter target,int depth,int i){
        int x,y,x1,y1;
        
            
        
        try{
            target.write("\n");
           target.write(new Integer(i).toString()); target.write(" ");target.write(new Integer(state_result).toString());
        for( y=0;y<7;y++){
            target.write("\n");
            for(x1=0;x1<depth;x1++) {
            
               target.write("        ");
        }
            for( x=0;x<7;x++) {
                if(sqr[y][x]==0)
                    target.write("0");
                if(sqr[y][x]==1)
                    target.write("#");
                if(sqr[y][x]==2)
                    target.write("*");
            }

        }} catch (Exception e) {}

    }

}
