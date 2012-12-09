import java.util.Date;


public class Article {
	private int id;
	private Date date;
	private String title;
	private String text;
	
	public Article() {
		
	}
	
	public Article(int nEWID, Date dATE, String tITLE, String bODY) {
		super();
		id = nEWID;
		date = dATE;
		title = tITLE;
		text = bODY;
	}
	
	public String toString(){
		return id + "\n" + date  + "\n" + title + "\n" + text;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int nEWID) {
		id = nEWID;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date dATE) {
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
