package stremaing.control;
import stremaing.streaming;
import java.awt.Button;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	
	public static void main(String[] args) {
		streaming str;
		String input1="",input2="",input="";
		WebSession ws = new WebSession();
		Scanner keyboard = new Scanner(System.in);
		do{

			try{			
				//System.out.println("Type command");	
				str=new streaming();
				str.recognize();
				String s=str.getSpeech();
				System.out.println(s);
				input = s;
				
				if(!input.equals("stop")){
					if(input.startsWith("right click")){
						ws.setText(input.substring(input.indexOf("k")+2, input.length()));
						ws.rightClick();
					}
					if(input.startsWith("mouse over")){
						ws.setText(input.substring(input.indexOf("r")+2, input.length()));
						ws.mouseOver();
					}
					else{
						input1 = input.substring(0, input.indexOf(" "));
						input2 = input.substring(input.indexOf(" ")+1,input.length());
						switch(input1){
						case "click":
							ws.setText(input2);
							ws.click();
							break;
						case "type":
							ws.inputText(input2);
							break;
						case "press":
							if(input2.equals("enter")){
								ws.pressEnter();
							}
							else if(input2.equals("tab")){
								ws.pressTab();
							}
							else if(input2.equals("up")){
								ws.pressUp();
							}
							else if(input2.equals("down")){
								ws.pressDown();
							}
							else{
								ws.setText(input2);
								ws.click();
							}
							break;
						case "scroll":
							if(input2.equals("down")){
								ws.pageDown();
							}
							else if(input2.equals("up")){
								ws.pageUp();
							}
							break;
						case "go":
							if(input2.equals("back")){
								ws.goBack();
							}
							else if(input2.equals("home")){
								ws.getHome();
							}
							else if(input2.substring(0,input2.indexOf(" ")).equals("to")){
								String site = input2.substring(input2.indexOf(" ")+1, input2.length());
								if(site.startsWith("https://")){
									ws.navTo(site);
								}
//								else if(site.startsWith("www.")){
//									ws.navTo("https://"+site);
//								}
								else{
									ws.navTo("https://"+site);
								}
							}
						break;

						}
					}
					

				}
				
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Oops! Something went wrong. Please try again");
				input1 = "";
			}
			
		}while(!input.equals("stop"));
		ws.quit();
	}


}
