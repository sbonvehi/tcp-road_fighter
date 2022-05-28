package road_fighter;

public class Config {
	
    public static final String STREET_COLOR = "#656567";
    public static final String GREEN = "#84E116";
    public static final String BLUE = "#0027D2";
    public static final String BEACH = "#D1CB76";

    public static final String STREET_LINE = "#ffffff";

    
    public static final int ANCHO_FRAME_ESCENA = 720;
    public static final int ALTO_FRAME_ESCENA = 800;
    public static final int ANCHO_FRAME_MAPA = 477;
    public static final int ALTO_FRAME_MAPA = 3700;
    
    
	public static final int ANCHO_AUTO = 30;
	public static final int ALTO_AUTO = 45;

	public static final String FONT_TYPE = "Verdana";
	public static final int FONT_SIZE_MARCADOR = 30;
	
	public static final String CAR_IMG = "file:src/main/resources/img/principal.png";
//	public static final String MAP_IMG = "file:src/main/resources/img/fondoCalle.png";
	public static final String MAP_IMG = "file:src/main/resources/img/fondoCalleLargo2.png";
    public static final String TREE_IMG = "file:src/main/resources/img/tree.png";
    public static final String TREES_IMG = "file:src/main/resources/img/trees.png";
    public static final String HOUSE_IMG = "file:src/main/resources/img/home.png";
    public final String CRASH_CAR = "file:src/main/resources/img/boom.png";
    public final static String BLUE_CAR = "file:src/main/resources/img/bluecar.png";
    public final static String YELLOW_CAR = "file:src/main/resources/img/yellowcar.png";
    public static final String[] ENEMIES = { 
        BLUE_CAR,
        YELLOW_CAR,}; 

	private Config() {
	}
 
}
