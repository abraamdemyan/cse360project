import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ActivityList {

	private class Activity{
		public String name;
		public int duration;
		public ArrayList<Activity> predecessors;
		public ArrayList<Activity> successors;
	}

	private ArrayList<Activity> first;
	private ArrayList<Activity> activities;
	private ArrayList<String> paths;

	public ActivityList() {
		first = new ArrayList<Activity>();
		activities = new ArrayList<Activity>();
	}

	public ArrayList<Activity> getFirst() { // returns ArrayList of first nodes
		return first;
	}

	// Adds an element to the front of the linked list.
	public void addFirst(String name, int duration)   
	{
		//create a new node
		Activity newActivity = new Activity();
		newActivity.name = name;
		newActivity.duration = duration;
		newActivity.predecessors = new ArrayList<Activity>();
		//add new node to first ArrayList
		first.add(newActivity);
		activities.add(newActivity);
		System.out.println(first.get(0).name);
	}

	public void add(String name, int duration, String pred) {
		//create a new node
		Activity newActivity = getActivity(name);
		if (newActivity==null) { //if activity does not already exist
			newActivity = new Activity();
		}
		newActivity.name = name;
		newActivity.duration = duration;
		newActivity.predecessors = new ArrayList<Activity>();
		newActivity.successors = new ArrayList<Activity>();
		String p[] = pred.split(", "); //get list of predecessors
		for (int i = 0; i < p.length; i++) { //for every predecessor
			Activity prevAct = getActivity(p[i]);
			if (prevAct != null) {
				prevAct.successors.add(newActivity);
				newActivity.predecessors.add(prevAct);
			}
			else if (!p[i].equals("")){
				Activity prev = new Activity();
				prev.name = p[i];
				newActivity.predecessors.add(prev);
			}
		}
		activities.add(newActivity);
	}

	public Activity getActivity(String name) {
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i).name.equals(name))
				return activities.get(i);
		}
		return null;
		/*Activity found = traverse(list, name);
		if (found != null)
			return found;
		else {
			for (int i = 0; i < list.size(); i++) {
				getActivity(list.get(i).successors, name);
			}
		}
		return null;*/
	}

	public String traverse(ArrayList<Activity> list) {
		for (int i = 0; i < list.size(); i++) {
			return list.get(i).name + ": " + list.get(i).duration + "; ";
		}
		return path;
	}
	
	public void calculatePaths() {
		String path = "";
		for (int i = 0; i < first.size(); i++) {
			path = first.get(i).name + ": " + first.get(i).duration + "; ";
			path = path + traverse(first.get(i).successors);
			
		}
	}

	public String getPaths() {
		String pathList = "";
		for (int i = 0; i < paths.size(); i++) {
			pathList = pathList + paths.get(i) + "\n"; 
		}
		return pathList;
	}

	public boolean deleteLinkedList() {
		first = new ArrayList<Activity>();
		activities = new ArrayList<Activity>();
		return (first.size() == 0 && activities.size() == 0);
	}

	public void printAll() {
		for (int i = 0; i < activities.size(); i++) {
			System.out.println(activities.get(i).name + " " + activities.get(i).duration);
			System.out.println("Pred:");
			for (int j = 0; j < activities.get(i).predecessors.size(); j++) {
				System.out.println(activities.get(i).predecessors.get(j).name + " " + activities.get(i).predecessors.get(j).duration);
			}
			System.out.println("Succ:");
			for (int j = 0; j < activities.get(i).successors.size(); j++) {
				System.out.println(activities.get(i).successors.get(j).name + " " + activities.get(i).successors.get(j).duration);
			}
		}

		System.out.println("\n\n\n\n\n\n");
	}


}
