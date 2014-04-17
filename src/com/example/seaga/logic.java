/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.seaga;
import java.io.FileWriter;

import android.util.Log;

/**
 *
 * @author mohammed
 */
// the squence of steps start by a app step followed by com step
// and so on
// * represent app peace # represent com peace
// 0 represent empty squer ,1 represent com peace ,2 represent app peace
public class logic {
    private int max_depth=7;
    private int depth;// current depth start with 0
    private step[] steps=new step[max_depth];
     private void add_com_up_state(int x,int y){
        int x1,y1;
        // important be sure of copy function to be correct
               steps[depth].states[steps[depth].state_num].copy(steps[depth-1].states[steps[depth-1].state_num-1]);
        //next we have to add change
        steps[depth].states[steps[depth].state_num].sqr[x][y]=0;// remove peace frome old place
        steps[depth].states[steps[depth].state_num].sqr[x][y-1]=1;// putting peace in the new place
        y=y-1;// y will be the new place of the peace
        // check for eating
        if((y>1)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-1]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-2]==1)){ // first check for eating upper peace
            steps[depth].states[steps[depth].state_num].sqr[x][y-1]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        if((x>2)&&(steps[depth].states[steps[depth].state_num].sqr[x-1][y]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x-2][y]==1)){ //  check for eating left peace
            steps[depth].states[steps[depth].state_num].sqr[x-1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        if((x<5)&&(steps[depth].states[steps[depth].state_num].sqr[x+1][y]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x+2][y]==1)){ // first check for eating write peace
            steps[depth].states[steps[depth].state_num].sqr[x+1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        if(steps[depth].states[steps[depth].state_num].state_result>steps[depth].best_state_result)
            steps[depth].best_state_result=steps[depth].states[steps[depth].state_num].state_result;
        steps[depth].state_num++;
        // no need to chech for eating down peace because it's imposable
    }
     private void add_com_down_state(int x,int y){
        int x1,y1;
        // important be sure of copy function to be correct
               steps[depth].states[steps[depth].state_num].copy(steps[depth-1].states[steps[depth-1].state_num-1]);
        //next we have to add change
        steps[depth].states[steps[depth].state_num].sqr[x][y]=0;// remove peace frome old place
        steps[depth].states[steps[depth].state_num].sqr[x][y+1]=1;// putting peace in the new place
        y=y+1;// y will be the new place of the peace
        // check for eating
        if((y<5)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+1]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+2]==1)){ // first check for eating down peace
            steps[depth].states[steps[depth].state_num].sqr[x][y+1]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        if((x>2)&&(steps[depth].states[steps[depth].state_num].sqr[x-1][y]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x-2][y]==1)){ //  check for eating left peace
            steps[depth].states[steps[depth].state_num].sqr[x-1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        if((x<5)&&(steps[depth].states[steps[depth].state_num].sqr[x+1][y]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x+2][y]==1)){ // first check for eating write peace
            steps[depth].states[steps[depth].state_num].sqr[x+1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        if(steps[depth].states[steps[depth].state_num].state_result>steps[depth].best_state_result)
            steps[depth].best_state_result=steps[depth].states[steps[depth].state_num].state_result;
        steps[depth].state_num++;
    }
    private void add_com_write_state(int x,int y){
        int x1,y1;
        // important be sure of copy function to be correct
               steps[depth].states[steps[depth].state_num].copy(steps[depth-1].states[steps[depth-1].state_num-1]);
        //next we have to add change
        steps[depth].states[steps[depth].state_num].sqr[x][y]=0;// remove peace frome old place
        steps[depth].states[steps[depth].state_num].sqr[x+1][y]=1;// putting peace in the new place
        x=x+1;// y will be the new place of the peace
        // check for eating
        if((y>2)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-1]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-2]==1)){ // first check for eating down peace
            steps[depth].states[steps[depth].state_num].sqr[x][y-1]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        if((x<5)&&(steps[depth].states[steps[depth].state_num].sqr[x+1][y]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x+2][y]==1)){ // first check for eating write peace
            steps[depth].states[steps[depth].state_num].sqr[x+1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        if((y<5)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+1]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+2]==1)){ // first check for eating down peace
            steps[depth].states[steps[depth].state_num].sqr[x][y+1]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        if(steps[depth].states[steps[depth].state_num].state_result>steps[depth].best_state_result)
            steps[depth].best_state_result=steps[depth].states[steps[depth].state_num].state_result;
        steps[depth].state_num++;
        //you have to ubdate steps[depth].best_state_result
    }
    private void add_com_left_state(int x,int y){
        int x1,y1;
        // important be sure of copy function to be correct
               steps[depth].states[steps[depth].state_num].copy(steps[depth-1].states[steps[depth-1].state_num-1]);
        //next we have to add change
        steps[depth].states[steps[depth].state_num].sqr[x][y]=0;// remove peace frome old place
        steps[depth].states[steps[depth].state_num].sqr[x-1][y]=1;// putting peace in the new place
        x=x-1;// y wi88ll be the new place of the peace
        // check for eating
        if((y<5)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+1]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+2]==1)) {// first check for eating down peace
            steps[depth].states[steps[depth].state_num].sqr[x][y+1]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        if((y>2)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-1]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-2]==1)){ // first check for eating down peace
            steps[depth].states[steps[depth].state_num].sqr[x][y-1]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        if((x>2)&&(steps[depth].states[steps[depth].state_num].sqr[x-1][y]==2)&&(steps[depth].states[steps[depth].state_num].sqr[x-2][y]==1)){ //  check for eating left peace
            steps[depth].states[steps[depth].state_num].sqr[x-1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result++;}
        //updating the best state result
        if(steps[depth].states[steps[depth].state_num].state_result>steps[depth].best_state_result)
            steps[depth].best_state_result=steps[depth].states[steps[depth].state_num].state_result;
        steps[depth].state_num++;
    }
    private int add_com_step(){
        int sucsses=0;
        int x=0,y=0;
        steps[depth].state_num=0;
        steps[depth].best_state_result=-100;
         for(x=0;x<7;x++){
            for(y=0;y<7;y++) {
                if(steps[depth-1].states[steps[depth-1].state_num-1].sqr[x][y]==1) {
                    if((y>0)&&(steps[depth-1].states[steps[depth-1].state_num-1].sqr[x][y-1]==0))
                    {add_com_up_state(x,y);sucsses=1;}//add the state of mving peace xy up
                    if((y<6)&&(steps[depth-1].states[steps[depth-1].state_num-1].sqr[x][y+1]==0))
                    {add_com_down_state(x,y);}
                    if((x>0)&&(steps[depth-1].states[steps[depth-1].state_num-1].sqr[x-1][y]==0))
                    {add_com_left_state(x,y);sucsses=1;}
                    if((x<6)&&(steps[depth-1].states[steps[depth-1].state_num-1].sqr[x+1][y]==0))
                    { add_com_write_state(x,y);sucsses=1;}
                }
            }
         }
        return sucsses;
    }
    private void add_app_up_state(int x,int y){
        int x1,y1;
        // important be sure of copy function to be correct
               steps[depth].states[steps[depth].state_num].copy(steps[depth-1].states[steps[depth-1].state_num-1]);
        //next we have to add change
        
        steps[depth].states[steps[depth].state_num].sqr[x][y]=0;// remove peace frome old place
        steps[depth].states[steps[depth].state_num].sqr[x][y-1]=2;// putting peace in the new place
        y=y-1;// y will be the new place of the peace
        // check for eating 
        if((y>1)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-1]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-2]==2)){ // first check for eating upper peace
            steps[depth].states[steps[depth].state_num].sqr[x][y-1]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        if((x>2)&&(steps[depth].states[steps[depth].state_num].sqr[x-1][y]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x-2][y]==2)){ //  check for eating left peace
            steps[depth].states[steps[depth].state_num].sqr[x-1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        if((x<5)&&(steps[depth].states[steps[depth].state_num].sqr[x+1][y]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x+2][y]==2)){ // first check for eating write peace
            steps[depth].states[steps[depth].state_num].sqr[x+1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        //updating the best state result
        if(steps[depth].states[steps[depth].state_num].state_result<steps[depth].best_state_result)
            steps[depth].best_state_result=steps[depth].states[steps[depth].state_num].state_result;
        steps[depth].state_num++;
        // no need to chech for eating down peace because it's imposable
    }
    private void add_app_down_state(int x,int y){
        int x1,y1;
        // important be sure of copy function to be correct
               steps[depth].states[steps[depth].state_num].copy(steps[depth-1].states[steps[depth-1].state_num-1]);
        //next we have to add change
        steps[depth].states[steps[depth].state_num].sqr[x][y]=0;// remove peace frome old place
        steps[depth].states[steps[depth].state_num].sqr[x][y+1]=2;// putting peace in the new place
        y=y+1;// y will be the new place of the peace
        // check for eating
        if((y<5)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+1]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+2]==2)){ // first check for eating down peace
            steps[depth].states[steps[depth].state_num].sqr[x][y+1]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        if((x>2)&&(steps[depth].states[steps[depth].state_num].sqr[x-1][y]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x-2][y]==2)){ //  check for eating left peace
            steps[depth].states[steps[depth].state_num].sqr[x-1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        if((x<5)&&(steps[depth].states[steps[depth].state_num].sqr[x+1][y]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x+2][y]==2)){ // first check for eating write peace
            steps[depth].states[steps[depth].state_num].sqr[x+1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        //updating the best state result
        if(steps[depth].states[steps[depth].state_num].state_result<steps[depth].best_state_result)
            steps[depth].best_state_result=steps[depth].states[steps[depth].state_num].state_result;
        steps[depth].state_num++;
    }
    private void add_app_write_state(int x,int y){
        int x1,y1;
        // important be sure of copy function to be correct
               steps[depth].states[steps[depth].state_num].copy(steps[depth-1].states[steps[depth-1].state_num-1]);
        //next we have to add change
       
        steps[depth].states[steps[depth].state_num].sqr[x][y]=0;// remove peace frome old place
        steps[depth].states[steps[depth].state_num].sqr[x+1][y]=2;// putting peace in the new place
        x=x+1;// y will be the new place of the peace
        // check for eating
        if((y<5)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+1]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+2]==2)){ // first check for eating down peace
            steps[depth].states[steps[depth].state_num].sqr[x][y+1]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        if((x<5)&&(steps[depth].states[steps[depth].state_num].sqr[x+1][y]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x+2][y]==2)){ // first check for eating write peace
            steps[depth].states[steps[depth].state_num].sqr[x+1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        if((y>2)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-1]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-2]==2)){ // first check for eating down peace
            steps[depth].states[steps[depth].state_num].sqr[x][y-1]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        //updating the best state result
        if(steps[depth].states[steps[depth].state_num].state_result<steps[depth].best_state_result)
            steps[depth].best_state_result=steps[depth].states[steps[depth].state_num].state_result;
        steps[depth].state_num++;
        //you have to ubdate steps[depth].best_state_result
    }
    private void add_app_left_state(int x,int y){
        int x1,y1;
        // important be sure of copy function to be correct
               steps[depth].states[steps[depth].state_num].copy(steps[depth-1].states[steps[depth-1].state_num-1]);
        //next we have to add change
        steps[depth].states[steps[depth].state_num].sqr[x][y]=0;// remove peace frome old place
        steps[depth].states[steps[depth].state_num].sqr[x-1][y]=2;// putting peace in the new place
        x=x-1;// x will be the new place of the peace
        // check for eating
        if((y<5)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+1]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x][y+2]==2)) {// first check for eating down peace
            steps[depth].states[steps[depth].state_num].sqr[x][y+1]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        if((y>2)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-1]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x][y-2]==2)){ // first check for eating down peace
            steps[depth].states[steps[depth].state_num].sqr[x][y-1]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        if((x>2)&&(steps[depth].states[steps[depth].state_num].sqr[x-1][y]==1)&&(steps[depth].states[steps[depth].state_num].sqr[x-2][y]==2)){ //  check for eating left peace
            steps[depth].states[steps[depth].state_num].sqr[x-1][y]=0;
            steps[depth].states[steps[depth].state_num].state_result--;}
        //updating the best state result
        if(steps[depth].states[steps[depth].state_num].state_result<steps[depth].best_state_result)
            steps[depth].best_state_result=steps[depth].states[steps[depth].state_num].state_result;
        steps[depth].state_num++;
    }
    private int add_app_step(){
        int sucsses=0;
        int x=0,y=0;
        steps[depth].state_num=0;
        steps[depth].best_state_result=100;
        for(x=0;x<7;x++){
            for(y=0;y<7;y++) {
                if(steps[depth-1].states[steps[depth-1].state_num-1].sqr[x][y]==2) {
                    if((y>0)&&(steps[depth-1].states[steps[depth-1].state_num-1].sqr[x][y-1]==0))
                    {add_app_up_state(x,y);sucsses=1;}//add the state of mving peace xy up
                    if((y<6)&&(steps[depth-1].states[steps[depth-1].state_num-1].sqr[x][y+1]==0))
                    {add_app_down_state(x,y);sucsses=1;}
                    if((x>0)&&(steps[depth-1].states[steps[depth-1].state_num-1].sqr[x-1][y]==0))
                    {add_app_left_state(x,y);sucsses=1;}
                    if((x<6)&&(steps[depth-1].states[steps[depth-1].state_num-1].sqr[x+1][y]==0))
                    { add_app_write_state(x,y);sucsses=1;}
                }
            }
        }
        
        return sucsses;
    }
    // add_new_step increas depth if depth is maximum depth
    //it will return -1 ,it will return 0 if there are no
    // avlable movement
    private int add_new_step(){
        int sucsses=-1;
        if(depth<max_depth){// this is the depth n of step which will be added
            if(depth%2==1)
                sucsses=add_com_step();
            else
                sucsses=add_app_step();
            steps[depth].max_state_num=steps[depth].state_num;
            //steps[depth].print_to_file(out_put, depth);
        }
        return sucsses;
    }
   
    private void ini_first_step(String a){
        int i=0;
        for(i=0;i<49;i++){
            if(a.charAt(i)=='#')
            {steps[0].states[0].sqr[i/7][i%7]=1;steps[0].best_state_result++;}
            if(a.charAt(i)=='*')
            {steps[0].states[0].sqr[i/7][i%7]=2;steps[0].best_state_result--;}
            if(a.charAt(i)=='0')
            steps[0].states[0].sqr[i/7][i%7]=0;
        }
        steps[0].state_num=1;
    }
    private void go_up_result_update(int target,int source){
        if(target>=0){
            steps[target].states[steps[target].state_num-1].state_result=steps[source].best_state_result;// ubdating th mother state
            if((target%2==0/*is it an com step?*/)&&(steps[target].best_state_result>steps[source].best_state_result)) //ubdating th mother step
                steps[target].best_state_result=steps[source].best_state_result;
            if((target%2==1/*is it an app step?*/)&&(steps[target].best_state_result<steps[source].best_state_result))
                steps[target].best_state_result=steps[source].best_state_result;
        }
    }
    public int go_up(){
        int x=1,cont=1;
        while(x==1){
            depth--;
            go_up_result_update(depth,depth+1);
            if(depth==0){x=0;cont=0;}
            if(steps[depth].state_num>1){x=0;steps[depth].state_num--;}
        }
        return cont;
    }
    private int get_steps_result(String target){ //target is the movement that the function will check
        int result=0,cont=1,adding_result;
        // before while loop we must inialize the first step
        //which will be a app step so depth=1
        //try{
        //out_put=new FileWriter("seega.txt");   } catch (Exception e) {}
        ini_first_step(target);
        //steps[0].states[steps[0].state_num-1].print_to_file(out_put, depth);
        depth=0;
        while(cont==1){
            depth++;
            //Log.d("delta11",new Integer(depth).toString());
            //try {out_put.write("\n");out_put.write("oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");} catch (Exception e) {}
            //try {out_put.flush();} catch (Exception e) {}
            if(add_new_step()<1){
                depth--;
                if(depth==(max_depth-1)||steps[depth].state_num==1)
                    cont=go_up();
                else
                    steps[depth].state_num--;
            }
        }
        return steps[0].best_state_result;

    }
    public String translat(){
        int result=-100,count,x,y,best_state=0,fromx=0,fromy=0,tox=0,toy=0;
        String dir=new String();
        for(count=0;count<steps[1].max_state_num;count++){
            if(steps[1].states[count].state_result>result){
                result=steps[1].states[count].state_result;
                best_state=count;
            }
        }//from
        System.out.print("best state= ");System.out.print(best_state);System.out.print("result ");
        System.out.print(steps[1].states[best_state].state_result);System.out.print("\n");
        for(y=0;y<7;y++){
            for(x=0;x<7;x++){
                if((steps[1].states[best_state].sqr[y][x]!=steps[0].states[0].sqr[y][x])&&(steps[0].states[0].sqr[y][x]==1)){
                    fromy=y;fromx=x;
                    x=8;y=8;
                }
            }
        }
        //to
        for(y=0;y<7;y++){
            for(x=0;x<7;x++){
                if((steps[1].states[best_state].sqr[y][x]!=steps[0].states[0].sqr[y][x])&&(steps[1].states[best_state].sqr[y][x]==1)){
                   toy=y;tox=x;
                    x=8;y=8;
                }
            }
        }
        // verfing if the movement is up or down or left or write
        if(toy!=fromy){// you have to check for movement symble !!
            if(toy>fromy)
                dir="d";//down move
            else
                dir="a";//up move
        }
        if(tox!=fromx){
            if(tox>fromx)
                dir="r";//down move
            else
                dir="l";//up move
        }
        x=fromy*7+fromx;
        String move;
	if(x>9)
         move=new String(new Integer(x).toString()+dir);
        else
	 move=new String("0"+new Integer(x).toString()+dir);
        return move;
    }
    public String do_logic(String board) {
        get_steps_result(board);
        //write the translating function here
        String result=translat();
        return result;
    }
    public void set_max_depth(int depth){
     max_depth=depth;
    }

   public logic(){
       for(int i=0;i<max_depth;i++)//try to check is't max_depth+1 ??
       steps[i]=new step();
   }

}
