package clips.agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import net.sf.clipsrules.jni.*;

public class Persons extends Agent {
  private Environment clips;
  
  protected void setup() {	
    clips = new Environment(); 
    addBehaviour(new TellBehaviour());
    addBehaviour(new AskBehaviour());
  } 

  private class TellBehaviour extends Behaviour {
    boolean tellDone = false;

    public void action() {
    

clips.clear();
clips.load("src\C\Users\geist\Desktop\CLIPSJNI\load-persons.clp");
clips.load("src\C\Users\geist\Desktop\CLIPSJNI\load-persons-rules.clp");
clips.reset();
clips.eval("(facts)");
       
      tellDone = true;
    } 
    
    public boolean done() {
      if (tellDone)
        return true;
      else
	return false;
    }
  }    // END of inner class ...Behaviour

 private class AskBehaviour extends Behaviour {
    boolean askDone = false;

    public void action() {
    	System.out.println("Invoking Ask"); 
    	try{


      clips.run();
      
      
      clips.clear();
      
      }  
      askDone = true;
    } 
    
    public boolean done() {
      if (askDone)
        return true;
      else
	return false;
    }
   
    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    } 
  }  
}
