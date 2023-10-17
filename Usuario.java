import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Usuario extends Thread
{
    private List<File> playList;
    private boolean exit = false;
    private String state = "menu";
    private File currentFile;

    Usuario(List<File> playList)
    {
        this.playList = playList;
    }

    @Override
	public void run()
    {
        System.out.println("Bienvenido al reproductor musical");
        try
        {
            while(!exit)
            {
                switch(state)
                {
                    case("menu"):
                        
                        System.out.println("Elija una opción o escriba salir");
                        int cont=1;
                        for (File file : playList) {
                            System.out.println(cont+". "+file.getName());
                            cont++;
                        }

                        String entradaTeclado = "";
                        Scanner entradaEscaner = new Scanner (System.in); 
                        entradaTeclado = entradaEscaner.nextLine ();
                        if(entradaTeclado.equals("salir"))
                        {
                            state = "salir";
                        }
                        else if( Integer.parseInt(entradaTeclado)>0 & Integer.parseInt(entradaTeclado)<=playList.size())
                        {
                            currentFile = playList.get(Integer.parseInt(entradaTeclado)-1);
                            state = "play";
                        }
                        else
                        {
                            System.out.println("Elija una opcion valida");
                        }

                        break;
                    case("play"):
                            System.out.println("Reproduciendo: "+ currentFile.getName());
                            Thread.sleep(300);
                        break;
                    case("standby"):
                            System.out.println("Para detener la cancion escriba stop");
                            entradaTeclado = "";
                            entradaEscaner = new Scanner (System.in);
                            entradaTeclado = entradaEscaner.nextLine ();

                            if(!entradaTeclado.equals("stop"))
                            {
                                System.out.println("Opcion incorrecta, elija de nuevo");
                            }
                            else
                            {
                                state = "stop";
                            }
                        break;
                }
            }
        }
        catch(Exception e)
        {
            
        }
    }

    public File getSong()
    {
        return currentFile;
    }

    public String getmyState()
    {
        return this.state;
    }

    public void setmyState(String state)
    {
        this.state = state;
    }

    public void stopUser()
    {
        this.exit = true;
    }
}
