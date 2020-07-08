package various;

public class LineEditor {
	
	private String text;
	private int insertionIndex;
	
	private String markedText;
	private int markedStartIndex;
	private int markedEndIndex;
	
	public LineEditor(String text, int index) {
		setText(text);
		setInsertionIndex(index);
		setMarkedText("");
		setMarkedStartIndex(0);
		setMarkedEndIndex(0);
	}
	
	public LineEditor() {
		setText("");
		setInsertionIndex(0);
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getInsertionIndex() {
		return insertionIndex;
	}
	
	public void setInsertionIndex(int insertionIndex) {
		// alternatively if avoid exceptions and have a more natural behaviour (that is, nothing happens)
//		if (insertionIndex >= 0 && insertionIndex <= text.length()) {
//			this.insertionIndex = insertionIndex;
//		}
		if (insertionIndex < 0) {
			throw new IllegalArgumentException("left setInsertionIndex out of bounds");
		}
		else if (insertionIndex > text.length()) {
			throw new IllegalArgumentException("right setInsertionIndex out of bounds");
		}
		this.insertionIndex = insertionIndex;
	}
	
	public String getMarkedText() {
		return markedText;
	}
	
	public void setMarkedText(String markedText) {
		this.markedText = markedText;
	}
	
	public int getMarkedStartIndex() {
		return markedStartIndex;
	}
	
	public void setMarkedStartIndex(int markedStartIndex) {
		this.markedStartIndex = markedStartIndex;
	}
	
	public int getMarkedEndIndex() {
		return markedEndIndex;
	}
	
	public void setMarkedEndIndex(int markedEndIndex) {
		this.markedEndIndex = markedEndIndex;
	}
	
	public void left() {
		left(1);
	}
	public void right() {
		right(1);
	}
	
	// alternatively if move to max possible position when index is out of bounds:
	// for left: if(insertionIndex - n < 0) set index to 0
	// for right: if(insertionIndex + n > text.length()) set index to text.length()
	public void left(int n) {
		if (insertionIndex - n >= 0) {
			setInsertionIndex(insertionIndex - n);
		}
	}
	public void right(int n) {
		if (insertionIndex + n <= text.length()) {
			setInsertionIndex(insertionIndex + n);
		}
	}
	
	public void deleteLeft() {
		if (!text.equals("") && insertionIndex != 0) {
			String sub = text.substring(0, insertionIndex - 1);
			String remainder = text.substring(insertionIndex);
			
			setText(sub + remainder);
			setInsertionIndex( insertionIndex - 1);
		}
	}
	
	public void deleteRight() {
		if (!text.equals("") && insertionIndex != text.length()) {
			String sub = text.substring(0, insertionIndex);
			String remainder = text.substring(insertionIndex + 1);
			
			setText(sub + remainder);
		}
	}
	 
	public void insertString(String s) {
		if (text.equals("")) {
			setText(s);
			setInsertionIndex( insertionIndex + s.length());
		}
		else {
			String sub = text.substring(0, insertionIndex);
			String remainder = text.substring(insertionIndex);
			
			setText(sub + s + remainder);
			setInsertionIndex( insertionIndex + s.length());
		}
	}
	
	public void insertObject(Object o) {
		if (text.equals("")) {
			setText("" + o); 
			setInsertionIndex( text.length());
		}
		else {
			String sub = text.substring(0, insertionIndex);
			String remainder = text.substring(insertionIndex);
			
			setText(sub + o + remainder);
			setInsertionIndex( text.length() - remainder.length());
		}
	}
	
	public void deleteMarking() {
		if (!markedText.equals("")) {
			String sub = text.substring(0, markedStartIndex);
			String remainder = text.substring(markedEndIndex);
			setText(sub + remainder);
			setInsertionIndex(markedStartIndex);
			setMarkedText("");
			setMarkedStartIndex(0);
			setMarkedEndIndex(0);
		}
	}
	
	public void replaceMarking(String s) {
		deleteMarking();
		insertString(s);
	}
	
	
	public void markLeft() {
		if (!text.equals("") && insertionIndex != 0) {
			if (markedText.equals("")) {
				//mark left for new marking
				setMarkedText(text.substring(insertionIndex -1, insertionIndex));
				setMarkedStartIndex(insertionIndex -1);
				setMarkedEndIndex(insertionIndex);
				
			}
			else if(insertionIndex == markedStartIndex) {
				// mark left for existing marking
				setMarkedText(text.substring(markedStartIndex -1, markedEndIndex));
				setMarkedStartIndex(markedStartIndex -1);
				
			}
			else if(insertionIndex == markedEndIndex){
				// unmark left for existing marking
				setMarkedText(text.substring(markedStartIndex, markedEndIndex -1));
				setMarkedEndIndex(markedEndIndex -1);
				if (markedText.equals("")) {
					setMarkedStartIndex(0);
					setMarkedEndIndex(0);
				}
			}
			setInsertionIndex(insertionIndex -1);
		}
	}
	
	public void markRight() {
		if (!text.equals("") && insertionIndex != 0) {
			if (markedText.equals("")) {
				//mark right for new marking
				setMarkedText(text.substring(insertionIndex, insertionIndex +1));
				setMarkedStartIndex(insertionIndex);
				setMarkedEndIndex(insertionIndex +1);
				
			}
			else if(insertionIndex == markedEndIndex) {
				// mark right for existing marking
				setMarkedText(text.substring(markedStartIndex, markedEndIndex +1));
				setMarkedEndIndex(markedEndIndex +1);
				
			}
			else if(insertionIndex == markedStartIndex){
				// unmark right for existing marking
				setMarkedText(text.substring(markedStartIndex +1, markedEndIndex));
				setMarkedStartIndex(markedStartIndex +1);
				if (markedText.equals("")) {
					setMarkedStartIndex(0);
					setMarkedEndIndex(0);
				}
			}
			setInsertionIndex(insertionIndex +1);
		}
	}
	

	public String toString() {
		if (text.equals("")) {
			return "My text is empty: |";
		}
		else {
			String sub = text.substring(0, insertionIndex);
			String remainder = text.substring(insertionIndex);
			
			return "My text is: " + sub + "|" + remainder;
		}
	}

	public static void main(String[] args) {
		LineEditor text = new LineEditor("consecutively", 8);
		System.out.println(text);
//		text.insertObject(text);
//		System.out.println(text);
		System.out.println(text.toString());
		text.right();
		text.right();
		System.out.println(text.toString());
		text.left();
		System.out.println(text.toString());
		text.insertString("bananaaa");
		System.out.println(text.toString());
		text.deleteLeft();
		System.out.println(text.toString());
		text.deleteRight();
//		System.out.println(text.toString());
//		text.markLeft();
//		System.out.println(text.toString());
//		System.out.println("marked text: " + text.getMarkedText() + " start: " + text.getMarkedStartIndex()+ " end: " + text.getMarkedEndIndex());
//		text.markLeft();
//		System.out.println(text.toString());
//		System.out.println("marked text: " + text.getMarkedText() + " start: " + text.getMarkedStartIndex()+ " end: " + text.getMarkedEndIndex());
//		text.deleteMarking();
//		System.out.println(text.toString());
//		System.out.println("marked text: " + text.getMarkedText() + " start: " + text.getMarkedStartIndex()+ " end: " + text.getMarkedEndIndex());
//		text.markRight();
//		System.out.println(text.toString());
//		System.out.println("marked text: " + text.getMarkedText() + " start: " + text.getMarkedStartIndex()+ " end: " + text.getMarkedEndIndex());
//		text.markRight();
//		System.out.println(text.toString());
//		System.out.println("marked text: " + text.getMarkedText() + " start: " + text.getMarkedStartIndex()+ " end: " + text.getMarkedEndIndex());
//		text.markRight();
//		System.out.println(text.toString());
//		System.out.println("marked text: " + text.getMarkedText() + " start: " + text.getMarkedStartIndex()+ " end: " + text.getMarkedEndIndex());
//		text.markRight();
//		System.out.println(text.toString());
//		System.out.println("marked text: " + text.getMarkedText() + " start: " + text.getMarkedStartIndex()+ " end: " + text.getMarkedEndIndex());
//		text.deleteMarking();
//		System.out.println(text.toString());
//		System.out.println("marked text: " + text.getMarkedText() + " start: " + text.getMarkedStartIndex()+ " end: " + text.getMarkedEndIndex());
	}

}

