import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainClass
{
    static Scanner sc = new Scanner(System.in);
    public static boolean ActiveBool = true;

    public static void main(String[] args) throws IOException
    {
        String FilePath;
        String FilePathNew;
        String Options;
        while (ActiveBool)
        {
            System.out.println("Inserire file path.");
            FilePath = sc.nextLine();
            FilePathNew = QuoteCheck(FilePath);
            System.out.println("La path è: " + FilePathNew + "; Confermi? \nYes: Si \nAnyWord: No");
            Options = (sc.nextLine()).toLowerCase();
            switch (Options)
            {
                case "yes":
                {
                    Encode(FilePathNew);
                    ActiveBool = false;
                    break;
                }
                default:
                {
                    System.out.println("Riprova");
                }
            }
        }
    }

    public static String QuoteCheck(String ParamInput)
    {
        return ParamInput.replace("\"", "");
    }

    static void Encode(String ParamDDS)
    {
        File DDSFile = new File(ParamDDS);
        BufferedImage Image;
        try
        {
            Image = ImageIO.read(DDSFile);
        }
        catch (IOException e)
        {
            System.out.println("Error reading image: " + e.getMessage());
            return;
        }
        if (Image == null)
        {
            System.out.println("Image " + DDSFile + " might be corrupted. Retry.");
            return;
        }
        File EncodedFile = new File("output.png");
        try
        {
            ImageIO.write(Image, "png", EncodedFile);
        }
        catch (IOException e)
        {
            System.out.println("Error writing image: " + e.getMessage());
        }
    }
}