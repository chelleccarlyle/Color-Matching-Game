import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * Chelle Cruz
 * 
 * CS 202
 * 
 */


public class ColorMatcher extends Application {
	
	//DATA FIELDS
	
	//Background of game
	private BorderPane pane = new BorderPane();
	//Menu to place buttons and labels
	private FlowPane menu;
	//Array of strings that represent 6 colors
	private String[] colors = new String[6];
	//Array of 6 color buttons
	private Button[] colorButtons = new Button[6];
	//Number of tries
	private int numOfTries;
	//Create new grid pane 
	private GridPane grid;
	//Status for game whether win or lose
	private boolean youWon;
	//Index for color
	private int index;
	//String representation of color of the first rectangle of the grid
	private String firstColor;
	
	//"Constructor" methods
	
	//Create default flow pane (menu)
	private void menuConstructor() {
		
		//Create new flow pane
		menu = new FlowPane();
		
		menu.setPadding(new Insets(16, 50, 16, 25));
	    menu.setHgap(5);
	    menu.setVgap(15);
	    
	    //Set orientation
	    menu.setOrientation(Orientation.VERTICAL);
	    
	    //Set alignment for flow pane
	    menu.setAlignment(Pos.CENTER);
	    
	    //Add default menu content
	    addDefaultMenuContent();
	    
	}
	
	private void addDefaultMenuContent() {
		
		//Add title of game
	    Text title = new Text("Color Matcher!");
	    title.setStyle("-fx-font-family: Helvetica; -fx-fill: #2b3a42; -fx-font-size: 25");
	    
	    Text name = new Text("By Chelle Cruz");
	    name.setStyle("-fx-font-family: Helvetica; -fx-font-size: 12; -fx-fill: #3f5765;");
	    
	    //Create button for new game
	    Button newGame = new Button("New Game");
	    newGame.setStyle("-fx-text-fill: #efefef; -fx-background-color: #ff530d; "
	    		+ "-fx-border-radius: 0; -fx-padding: 10 20 10 20;");
	    
	    // Place nodes in the pane
	    menu.getChildren().addAll(title, name, newGame);
	
	    
	    //Set action for new game button
	    newGame.setOnAction(e -> {
	    	
	    	//Start new game
	    	newGame();
	    	
	    });
		
	}
	
	//Create default grid with random colors
	private void gridConstructor() {
		
		//Create new grid pane
		grid = new GridPane();
		
		//Show the grid lines
		grid.setGridLinesVisible(true);
		
		//Add squares to grid pane (15 * 15)
		for (int i = 0; i < 15; i++) {
			
			for (int j = 0; j < 15; j++) {
				
				Rectangle r = new Rectangle(43, 43);
				//r.setStroke(Color.valueOf("#2b3a42"));
				
				//Fill rectangle with random color from array of colors
				r.setFill(Color.color(Math.random(), Math.random(), Math.random()));
				
				//Add to grid
				grid.add(r, i, j);
				
			}
			
		}
		
	}
	
	private void buttonsConstructor() {
		
		//Create default buttons and add to flow pane (menu)
	    for (int i  = 0; i < colorButtons.length; i++) {
	    	
	    	Button b = new Button();
	    	b.setPadding(new Insets(20, 50, 20, 50));
	    	b.setStyle("-fx-border-radius: 2");
	    	
	    	colorButtons[i] = b;	//Add button to color button array
	
	    }
		
	}
	
	//Getters and setters
	private Rectangle getDeepCopyRectangleFromGrid(int col, int row) {
		
		//Create rectangle result
		Rectangle result = null;
		
	    for (Node r : grid.getChildren()) {
	    	
	    	//Check if the node has no null on column index or row index
	    	//Then check if it matches with col and row
	        if (GridPane.getColumnIndex(r) != null && 
	        GridPane.getRowIndex(r) != null &&
	        GridPane.getColumnIndex(r).intValue() == col && 
	        GridPane.getRowIndex(r).intValue() == row) {
	        	
	        	System.out.println("Column Index: " + GridPane.getColumnIndex(r).intValue() +
		            		"\nRow Index: " + GridPane.getRowIndex(r).intValue());
	        	
	        	//Get width, height, and color from r
	        	double width = ((Rectangle)r).getWidth();
	        	double height = ((Rectangle)r).getHeight();
	        	Color color = (Color)((Rectangle)r).getFill();
	        	
	        	result = new Rectangle(width, height, color);
	        	result.setStroke(Color.GRAY);
	        	
	        	return result;
	        	
	        }
	        
	    }
	    
	    return result;
	    
	}
	
	
	//This returns a shallow copy of a rectangle from the grid
	//Original rectangle will be affected
	private Rectangle getRectangleFromGrid(int col, int row) {
		
		//Create rectangle result
		Rectangle result = null;
		
	    for (Node r : grid.getChildren()) {
	    	
	    	//Check if the node has no null on column index or row index
	    	//Then check if it matches with col and row
	        if (GridPane.getColumnIndex(r) != null && 
	        GridPane.getRowIndex(r) != null &&
	        GridPane.getColumnIndex(r).intValue() == col && 
	        GridPane.getRowIndex(r).intValue() == row) {
	       	
	            return result = (Rectangle)r;
	        	
	        }
	        
	    }
	    
	    return result;
	    
	}
	
	//Hexadecimal string
	private String getRGB(Color color) {
		
		return String.format("#%02X%02X%02X",
	            (int)(color.getRed() * 255),
	            (int)(color.getGreen() * 255),
	            (int)(color.getBlue() * 255));
		
	}
	
	private void setBorderPaneStyle() {
		
		//Set background color of pane
		pane.setStyle("-fx-background: #efefef");
				
		//Add padding
		pane.setPadding(new Insets(16, 16, 16, 16));
		
	}
	
	private void setColor(String color, int index) {
		
		colors[index] = color;
		
	}
	
	private void setButtonsColors() {
		
		for (int i  = 0; i < colorButtons.length; i++) {
			colorButtons[i].setStyle("-fx-background-color: " + colors[i]);
		}
		
	}
	
	//Create a variable length parameter list to add nodes to menu
	private void setMenu(Node ... nodes) {
		
		//Remove all content from menu
		menu.getChildren().clear();
		
		//Add default menu content
		addDefaultMenuContent();
	    
	    //Add to menu
		for (Node n: nodes) {
	    	menu.getChildren().add(n);
		}
			    
	}
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		
		//Set style of border pane
		setBorderPaneStyle();
		
		//Create 6 default buttons
		buttonsConstructor();
				
		//Set default colors to gray
		for (int i = 0; i < colors.length; i++) {
			setColor("gray", i);
		}
					
		//Set the color of each button
		setButtonsColors();
		
		//Create and construct default grid pane
		gridConstructor();
		
		//Create and construct default flow pane menu
		menuConstructor();
		
		//Place grid pane and flow pane in border pane
		pane.setLeft(grid);
		pane.setCenter(menu);
		
		// Create a scene and place it in the stage
	    Scene scene = new Scene(pane, 1000, 700);
	    primaryStage.setTitle("Color Matcher!"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	    
	    //Add listener to keep size of the display resizable to window adjustment
	    pane.widthProperty().addListener(ov -> {
	    	menu.setMaxWidth(pane.getWidth());
	    	grid.setMaxWidth(pane.getWidth());
	    });
	    
	    pane.heightProperty().addListener(ov -> {
	    	menu.setMaxHeight(pane.getHeight());
	    	grid.setMaxHeight(pane.getHeight());
	    });
	    	
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void newGame() {
		
		//Allow user to pick 6 colors from grid
		pickColors();
		
	}
	
	private void pickColors() {
		
		//Randomize colors of the rectangles of the grid
		for (Node r: grid.getChildren()) {
			
			if (GridPane.getColumnIndex(r) != null && 
	        GridPane.getRowIndex(r) != null) {
			
				((Rectangle)r).setFill(Color.color(Math.random(), Math.random(), Math.random()));
			
			}
			
		}
			
		//Set default colors to gray
		for (int i = 0; i < colors.length; i++) {
			setColor("gray", i);
		}
		
		//Set color buttons
		setButtonsColors();
		
		//Create label or text which tells user to pick 6 colors from the grid
		Text pickColors = new Text("Pick 6 colors from the grid.");
		Text click = new Text("Click on any colored square!");
		pickColors.setStyle("-fx-font-family: Avenir");
		click.setStyle("-fx-font-family: Avenir");
		
		//Update menu with text and color buttons
		setMenu(pickColors, click, colorButtons[0], colorButtons[1], colorButtons[2],
				colorButtons[3], colorButtons[4], colorButtons[5]);
		
		//Create number of chosen colors (index)
		index = 0;
	
		for (Node r: grid.getChildren()) {
			
			//Make each rectangle clickable
			r.setOnMouseClicked(e -> {
				
				String color = getRGB((Color)((Rectangle)r).getFill());
 				
				setColor(color, index);
				
				//Set color to color button
				colorButtons[index].setStyle("-fx-background-color: " + colors[index]);
				
				index++;
				
				if (index == 6) {
					startGame();
				}
				
			});
				
		}	
		
	}
	
	private void startGame() {
		
		//Make the rectangles unclickable
		for (Node r: grid.getChildren()) {
			r.setOnMouseClicked(null);
		}
			
		//Randomize colors of the rectangles of the grid
		for (int i = 0; i < grid.getChildren().size(); i++) {
			
			grid.getChildren().get(i).setStyle("-fx-fill: " + colors[(int)(Math.random() * 6)]);
			
		}
		
		//Set the number of tries
		numOfTries = 30;
		
		//Create labels
	    Label tries = new Label("Tries Left: " + numOfTries);
	    Label pickColor = new Label("Pick a color below!");
	    tries.setStyle("-fx-font-family: Avenir");
	    pickColor.setStyle("-fx-font-family: Avenir");
	    
		//Update menu 
	    //Add the labels and the color buttons
		setMenu(tries, pickColor, colorButtons[0], colorButtons[1], colorButtons[2],
				colorButtons[3], colorButtons[4], colorButtons[5]);
		
		for (int i = 0; i < colorButtons.length; i++) {
			
			int index = i;
			
			colorButtons[i].setOnAction(e -> {
					
				//Update number of tries
	    		numOfTries--;
	    		menu.getChildren().remove(tries);
	    		tries.setText("Tries Left: " + numOfTries);
	    		menu.getChildren().add(3, tries);
	    		
	    		//Match colors
	    		matchColors(colorButtons[index]);
	    		
	    		//Set youWon to true
	    		youWon = true;
	    		
	    		//Check if all colors in the grid are the same
	    		for (int col = 0; col < 15; col++) {
	    			
	    			for (int row = 0; row < 15; row++) {
	    				
	    				//Get first color
	    				firstColor = getRGB((Color)getRectangleFromGrid(0, 0).getFill());
	    				
	    				//Get other color from rectangle being compared to
	    				String otherColor = getRGB((Color)getRectangleFromGrid(col, row).getFill());
	    				
	    				if (isSame(firstColor, otherColor) == false) {
	    					youWon = false;
	    					break;
	    				}
	    				
	    			}
	    			
	    		}
	    		
	    		//After checking all rectangles in the grid, youWon is still true
	    		
	    		if (numOfTries == 0 || youWon == true) {
	    			
	    			//Remove color buttons
	    			menu.getChildren().removeAll(colorButtons);
	    			//Remove pick color label
	    			menu.getChildren().remove(pickColor);
	    			
	    			//Add a text for status
	    			Text status = new Text();
	    			status.setStyle("-fx-font-family: Helvetica; -fx-fill: #ff530d; -fx-font-size: 50;"
	    					+ "-fx-font-weight: bold");
	    			menu.getChildren().add(0, status);
	    			
	    			//Set status of text
	    			if (youWon == true) {
	    				status.setText("YOU WON!");
	    			}
	    			else {
	    				status.setText("YOU LOST!");
	    			}
	    			
	    		}
	    		
	    	});
			
		}
		
	}
	
	private void matchColors(Button selected) {
	
		//Get the color from the selected button
		String selectedColor = selected.getStyle().substring(22);
		
		//Get first rectangle in the grid
		Rectangle first = getRectangleFromGrid(0, 0);
		
		//Get the color from the first rectangle
		firstColor = getRGB((Color)first.getFill());
		
		//Set the color of the first rectangle to the selected color
		first.setFill(Color.valueOf(selectedColor));
		
		//Match with rectangles that share an edge and have original color (first color)
		matchNext(getRectangleFromGrid(0, 0), firstColor, selectedColor);
		
	}
	
	private void matchNext(Rectangle r, String firstColor, String selectedColor) {
		
		//Get column index and row index from r
		int column = GridPane.getColumnIndex(r);
		int row = GridPane.getRowIndex(r);
		
		//Compare with rectangles next to r (share an edge):
		
		//Check if the r is in the last column or last row in the grid (15th)
		//14 (index wise)
		
		//If last column do not compare to rectangle at right (there is none)
		if (column != (15 - 1)) {
		
			//Get rectangle to the right of r
			Rectangle right = getRectangleFromGrid(column + 1, row);
			
			//Compare if rectangle has same color as first color or selected color
			if (isSame(getRGB((Color)right.getFill()), firstColor)) {
				
				//Set color of right rectangle to selected color of button
				right.setFill(Color.valueOf(selectedColor));
				
				//Use match next method to have that compare to rectangles that share edge
				matchNext(right, firstColor, selectedColor);
				
			}
		
		} 
		
		//If last row do not compare to rectangle below (there is none)
		if (row != (15 - 1)) {
		
			//Get rectangle from below r
			Rectangle below = getRectangleFromGrid(column, row + 1);
			
			//Compare if rectangle has same color as first color
			if (isSame(getRGB((Color)below.getFill()), firstColor)) {
				
				//Set color of rectangle below to selected color of button
				below.setFill(Color.valueOf(selectedColor));
				
				//Use match next method to have that compare to rectangles that share edge
				matchNext(below, firstColor, selectedColor);
				
			}
		
		}
		
		//Check if the r is in the first column or first row in the grid (1st)
		//0 (index wise)
		
		//If first column do not compare to rectangle at left (there is none)
		if (column != 0) {
			
			//Get rectangle to left of r
			Rectangle left = getRectangleFromGrid(column - 1, row);
			
			//Compare if rectangle has same color as first color or selected color
			if (isSame(getRGB((Color)left.getFill()), firstColor)) {
				
				//Set color of left rectangle to selected color of button
				left.setFill(Color.valueOf(selectedColor));
				
				//Use match next method to have that compare to rectangles that share edge
				matchNext(left, firstColor, selectedColor);
				
			}
			
		}
		
		//If first row do not compare to rectangle above (there is none)
		if (row != 0) {
			
			//Get rectangle from above r
			Rectangle above = getRectangleFromGrid(column, row - 1);
			
			//Compare if rectangle has same color as first color or selected color
			if (isSame(getRGB((Color)above.getFill()), firstColor)) {
				
				//Set color of rectangle above to selected color of button
				above.setFill(Color.valueOf(selectedColor));
				
				//Use match next method to have that compare to rectangles that share edge
				matchNext(above, firstColor, selectedColor);
				
			}
			
		}
		
	}
	
	private boolean isSame(String color1, String color2) {
		
		if (color1.compareToIgnoreCase(color2) == 0) {
			return true;
		}
		else {
			return false;
		}
		
	}
		
}
