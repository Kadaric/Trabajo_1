import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Trabajo_1
{
    private static File[] files;
    private static String extFile = ".wav";
    private static boolean exit = false;
	public static void main(String [] args)
	{
        File f = new File( "." );

        try
        {
            if ( f.isDirectory()) {

                files = f.listFiles();

                List<File> filteredFiles = Arrays.stream(files).filter(file -> file.isFile() && file.getName().endsWith(extFile)).collect(Collectors.toList());

                Usuario user = new Usuario(filteredFiles);
                Reproductor rep = new Reproductor();

                user.start();
                rep.start();
                
                while(!exit)
                { 
                    String userState = user.getmyState();

                    switch(userState)
                    {
                        case "play":
                            File song = user.getSong();
                            if(song != null)
                            {
                                rep.playSong(song.getName());
                            }
                            user.setmyState("standby");
                        break;

                        case "stop":
                            rep.stopSong();
                            user.setmyState("menu");
                        break;

                        case "salir":
                            
                            exit = true;
                            user.stopUser();
                            rep.stopRepro();;

                        break;
                    }
                }
                System.out.println("Gracias por usar el reproductor");
            }       
        }
        catch(Exception e)
        {

        }

    }
}