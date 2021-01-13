package Lab5.simulator;

/**
*
* Event is a class that describes what a general event contains.
*/
public abstract class Event {

   //Instance variables
   private EventQueue queue;
   private double timeStamp;

   /**
    *
    * @param queue the queue so every event can put them self inside of it.
    * @param timeStamp the time where the events effect starts.
    */
   public Event(EventQueue queue, double timeStamp){
       this.queue = queue;
       this.timeStamp = timeStamp;
   }


   // ---------- Getters ----------

   /**
    * @return the EventQueue.
    */
   public EventQueue getQueue(){
       return queue;
   }

   /**
    * @return the start time of the event.
    */
   public double getTimeStamp() {
       return timeStamp;
   }

   // ---------- end Getters ---------

   /**
    * The abstract method "eventTriggered" that every event has.
    */
   public abstract void eventTriggered();
}