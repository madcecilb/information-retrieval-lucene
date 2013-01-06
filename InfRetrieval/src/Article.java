
public class Article {
	private int id;
	private String date;
	private String title;
	private String text;
	
	public Article() {
		id = 0;
		date = null;
		title = "";
		text = "";
	}
	
	public Article(int nEWID, String dATE, String tITLE, String bODY) {
		super();
		id = nEWID;
		date = dATE;
		title = tITLE;
		text = bODY;
	}
	
	public String toString() {
		return id + "\n" + date  + "\n" + title + "\n" + text;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int nEWID) {
		id = nEWID;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String dATE) {
		date = dATE;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String tITLE) {
		title = tITLE;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String bODY) {
		text = bODY;
	}
}
