package various;

public class Rectangle {
	
	private int minX, minY, maxX, maxY;
	private boolean isEmpty;
	
	public Rectangle(int minX, int maxX, int minY, int maxY) {
		setMinX(minX);
		setMinY(minY);
		setMaxX(maxX);
		setMaxY(maxY);
		setEmpty(false);
	}
	
	public Rectangle() {
		setMinX(0);
		setMinY(0);
		setMaxX(0);
		setMaxY(0);
		setEmpty(true);
	}
	
	public int getMinX() {
		return minX;
	}
	
	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	//width and heigth is the number of points in the plane, not the distance between them
	public int getWidth() {
		if (isEmpty) {
			return 0;
		}
		else if(maxX-minX == 0) {
			return 1;
		}
		else {
			return maxX-minX +1;
		}
	}
	
	public int getHeight() {
		if (isEmpty) {
			return 0;
		}
		else if(maxY-minY == 0) {
			return 1;
		}
		else {
			return maxY-minY +1;
		}
	}
	
	public boolean contains(int x, int y) {
		return (!isEmpty()) && (x <= maxX && x >= minX && y <= maxY && y >= minY);
	}
	
	public boolean contains(Rectangle rect) {
		return (!isEmpty()) && (!rect.isEmpty()) && contains(rect.getMinX(), rect.getMinY()) && contains(rect.getMaxX(), rect.getMaxY());
	}
	
	public boolean add(int x, int y) {
		if (isEmpty) {
			setMinX(x);
			setMinY(y);
			setMaxX(x);
			setMaxY(y);
			setEmpty(false);
			return true;
		}
		else if(contains(x,y)){
			return false;
		}
		else {
			if (x < minX) {
				setMinX(x);
			}
			else if(x > maxX) {
				setMaxX(x);
			}
			
			if (y < minY) {
				setMinY(y);
			}
			else if(y > maxY){
				setMaxY(y);
			}
			return true;
		}
	}
	
	public boolean add(Rectangle rect) {
		if (rect.isEmpty) {
			return false;
		}
		else if (isEmpty) {
			setMinX(rect.getMinX());
			setMinY(rect.getMinY());
			setMaxX(rect.getMaxX());
			setMaxY(rect.getMaxY());
			setEmpty(false);
			return true;
		}
		else if(contains(rect)) {
			return false;
		}
		else {
			if (rect.getMinX() < minX) {
				setMinX(rect.getMinX());
			}
			else if(rect.getMaxX() > maxX) {
				setMaxX(rect.getMaxX());
			}
			
			if (rect.getMinY() < minY) {
				setMinY(rect.getMinY());
			}
			else if(rect.getMaxY() > maxY){
				setMaxY(rect.getMaxY());
			}
			return true;
		}
	}
	
	public Rectangle union(Rectangle rect) {
		Rectangle newUnionRect = new Rectangle();
		// return empty object if union is impossible
		if (isEmpty || rect.isEmpty || contains(rect) || rect.contains(this) ) {
			return newUnionRect;
		}
		newUnionRect.setMinX(Math.min(getMinX(), rect.getMinX()));
		newUnionRect.setMaxX(Math.max(getMaxX(), rect.getMaxX()));
		newUnionRect.setMinY(Math.min(getMinY(), rect.getMinY()));
		newUnionRect.setMaxY(Math.max(getMaxY(), rect.getMaxY()));
		
		newUnionRect.setEmpty(false);
        return newUnionRect;
	}
	
	public Rectangle intersection(Rectangle rect) {
		Rectangle newIntersectionRect = new Rectangle();
		// return empty object if intersection is impossible
		if (isEmpty || rect.isEmpty || contains(rect) || rect.contains(this) || !intersects(rect)) {
			return newIntersectionRect;
		}
		newIntersectionRect.setMinX(Math.max(getMinX(), rect.getMinX()));
		newIntersectionRect.setMaxX(Math.min(getMaxX(), rect.getMaxX()));
		newIntersectionRect.setMinY(Math.max(getMinY(), rect.getMinY()));
		newIntersectionRect.setMaxY(Math.min(getMaxY(), rect.getMaxY()));
		
		newIntersectionRect.setEmpty(false);
		return newIntersectionRect;
	}
	
	public boolean intersects(Rectangle rect) {
		boolean approvedX = false;
		boolean approvedY = false;
		
		for (int i = this.getMinX(); i <= this.getMaxX(); i++) {
			for (int j = rect.getMinX(); j <= rect.getMaxX(); j++) {
				if (i == j) {
					approvedX = true;
				}
			}
		}
		
		for (int i = this.getMinY(); i <= this.getMaxY(); i++) {
			for (int j = rect.getMinY(); j <= rect.getMaxY(); j++) {
				if (i == j) {
					approvedY = true;
				}
			}
		}
		
		if (approvedX && approvedY) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return "Rectangle:\nMax x: " + this.maxX + "\nMin x: " + this.minX + "\nMax y: " + this.maxY + "\nMin y: " + this.minY + "\nEmpty: " + this.isEmpty + "\nHeigth: " + this.getHeight() + "\nWidth: " + this.getWidth() ;
	}
	
	public static void main(String[] args) {
		Rectangle myRect = new Rectangle();
		Rectangle rect2 = new Rectangle();
//		Rectangle myUnion = new Rectangle();
//		Rectangle myIntersection = new Rectangle();
		System.out.println(myRect.toString());
		System.out.println(myRect.contains(1,1));
		System.out.println(myRect.contains(0,0));
		myRect.add(2, 3);
		System.out.println(myRect.toString());
		myRect.add(5, 7);
		System.out.println(myRect.toString());
		myRect.add(3, 1);
		System.out.println(myRect.toString());
		myRect.add(4, 5);
		System.out.println(myRect.toString());
		myRect.add(4, 7);
		System.out.println("last myRect" + myRect.toString());
		rect2.add(2, 3);
		rect2.add(4, 6);
		System.out.println(myRect.contains(rect2));
		rect2.add(1, 9);
		System.out.println(myRect.contains(rect2));
		System.out.println("last rect2" + rect2.toString());
		
		System.out.println("intersects?" + myRect.intersects(rect2));
		
		//either add or union or intersection. Validation stops testing them consecutively
		myRect.add(rect2);
		System.out.println("myRect after add with rect 2" + myRect.toString());
//		myUnion = myRect.union(rect2);
//		System.out.println("union of myRect and rect2" + myUnion.toString());
//		myIntersection = myRect.intersection(rect2);
//		System.out.println("intersection of myRect and rect2" + myIntersection.toString());
		
		Rectangle test1 = new Rectangle(15,17,-33,33);
		Rectangle test2 = new Rectangle(-11,13,-27,23);
		System.out.println("test1" + test1.toString());
		System.out.println("test2" + test2.toString());
		System.out.println("tests intersects?" + test1.intersects(test2));

//		myIntersection = test1.intersection(test2);
//		System.out.println("intersection of myRect and rect2" + myIntersection.toString());
	}

}

