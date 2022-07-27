public class Otage extends Humain{

    public static final String RED = "\u001B[31m";
    public static final String DEFAULT = "\u001B[0m";

    // Les attributs
    public static int cptO = 0;
    private int id = 0;

    // Constructeurs

    private Otage(int posX, int posY)                                           // On interdit le positionnement des braqueurs là où se trouve les otages
    {
      super(posX,posY);
      this.id = cptO+1;
      cptO++;
    }

    public Otage()
    {
      this( (TestSimulation.MAX_X)-1, (TestSimulation.MAX_Y)-1 );                     // tous nos otages sont placés à l'éxtrémité droite et basse de notre terrain
    }

    // Accesseurs
    public int getId()
    {
      return this.id;
    }

    public String toString()
    {
    return "Est un otage qui a pour "+super.toString()+" et qui est apeuré\n";
    }

}
