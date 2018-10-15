package lab1b;
import java.awt.Color;
import java.awt.event.MouseEvent;
import studijosKTU.ScreenKTU;
       
public class Ekranai extends ScreenKTU
{
    public static void main(String[] args) 
    {
        pirmasEkranas();
    }

    static void pirmasEkranas()
    {          
        ScreenKTU sc1 = new ScreenKTU(22, 50, ScreenKTU.Grid.OFF);

        sc1.fillRect(5, 5, 10, 10, Color.black);
        sc1.fillBorder(5, 5, 10, 10, Color.blue);

        sc1.fillRect(5, 20, 10, 10, Color.black);
        sc1.fillBorder(5, 20, 10, 10, Color.blue);

        sc1.fillRect(5, 35, 10, 10, Color.black);
        sc1.fillBorder(5, 35, 10, 10, Color.blue);

        sc1.fillRect(20, 5, 10, 10, Color.black);
        sc1.fillBorder(20, 5, 10, 10, Color.blue);

        sc1.fillRect(20, 20, 10, 10, Color.black);
        sc1.fillBorder(20, 20, 10, 10, Color.blue);

        sc1.refresh(); 

        pirmasRastas(sc1, 8, 2, 6, 6);
        antrasRastas(sc1, 16, 2, 7, 21);
        treciasRastas(sc1, 16, 2, 7, 36);
        ketvirtasRastas(sc1, 16, 21, 6);
        penktasRastas(sc1, 8, 21, 21);  
        
        ScreenKTU sc2 = new ScreenKTU(25, 25, Grid.ON);
        
        pirmasRastas(sc2, 25, 2, 0, 0);
        antrasRastas(sc2, 25, 2, 0, 0);
        treciasRastas(sc2, 25, 2, 0, 0);
        ketvirtasRastas(sc2, 25, 0, 0);
        penktasRastas(sc2, 25, 0, 0);  
        
        sc1.refresh(); 

    }

    static void pirmasRastas(ScreenKTU sc, int n, int h, int pr, int pb)
    {       
        
        if (pr == 0 || pb == 0)
        {
            for(int k=h-1; k<n+n; k+=h)
            {
                if (k < n)
                {
                    for(int i=k, j=0; i>=0; j++, i--)
                    {
                        sc.print(i, j, Color.red);
                        sc.refresh();
                    } 
                }
                else 
                {
                    for (int i=k, j=0; j<n; i--, j++)
                        if (i<=n-1)
                        {
                            sc.print(i, j, Color.red);
                            sc.refresh();
                        }
                }
                
            }
                
        }
        else
        {   
            for(int k=pr; k<pr+n+pb+1; k+=h)
            {
                if (k < n+pr)
                    for(int i=k, j=pb; i>=n-h; j++, i--)
                    {
                        sc.print(i, j, Color.red);
                        sc.refresh();
                    }         
                else
                {
                    for (int i=k, j=pb; j<pb+n; i--, j++)
                        if (i<=pr+n-1)
                        {
                            sc.print(i, j, Color.red);
                            sc.refresh();
                        }
                }               
            }
        }
    }

    static void antrasRastas(ScreenKTU sc, int n, int h, int pr, int pb)
    {
        if (pr == 0 || pb == 0)
        {
            int d = 0;
            for(int k=0; k<n; k+=h)
            {
                for(int i=0, j=d; i<n; i++)
                {
                    if (j<n)
                    {
                        sc.print(i, j, Color.yellow);
                        sc.refresh(); 
                    }
                }
                d+=h;
            }
        }
        else
        {    
            int c = pr+pb;
            for(int k=pr-1; k<pb; pb+=h)
                for (int i=k, j = pb; i<n-h; i++)
                    if (j<c)
                    {
                        sc.print(i, j, Color.yellow);
                        sc.refresh(); 
                    }   
            }
    }

    static void treciasRastas(ScreenKTU sc, int n, int h, int pr, int pb)
    {
        if (pr == 0 || pb == 0)
        {
            int d = 0;
            for(int k=0; k<n; k+=h)
            {
                for(int i=d, j=0; j<n; j++)
                {
                    if (i<n)
                    {
                        sc.print(i, j, Color.green);
                        sc.refresh();  
                    }
                    
                }
                d+=h;
            }
        }
        else
        {
            int c = pr+pb;
            for (int k = pr; k < n/2; k+=h)
                for (int i = k, j = pb; i < n-h; j++)
                    if (j<c+1)
                    {
                        sc.print(i, j, Color.green);
                        sc.refresh(); 
                    }
                    else
                    {
                        j = pb-1;
                        i +=h;
                    }
        }
    }

    
    static void ketvirtasRastas(ScreenKTU sc, int n, int pr, int pb)
    {
        if (pr == 0 || pb == 0)
        {
            for (int i = 0; i < n; i++)
                for (int j = 0; j < i+1 && j < n; j++)
                {
                    sc.print(i, j, Color.orange);
                    sc.refresh();
                } 
        }
        else
        {
            for (int i = pr; i < pr + n/2; i++)
                for (int j = pb; j <= (pb+i) - pr; j++) 
                {
                    sc.print(i, j, Color.orange);
                    sc.refresh();
                }
        }
        
    }

    static void penktasRastas(ScreenKTU sc, int n, int pr, int pb)
    {
        if (pr == 0 || pb == 0)
        {
            int c = n;
            for (int i = 0; i <= n; i++)
            {
                for (int j = 0; j <= c/2; j++)              
                {              
                    sc.print(i, j, Color.PINK);
                    sc.refresh();
                }
                c -= 2;
            }
            for (int i = 0; i <= n; i++)
            {
                for (int j = 0; j <= c/2; j++)
                {
                    sc.print(i, j, Color.PINK);
                    sc.refresh();
                }
                c += 2;
            }
        }
        else 
        {
            int c = n;
            for (int i = pr; i <= pr + c + 1; i++)
            {
                for (int j = pb; j <= (pr + c/2) - 1; j++)              
                {              
                    sc.print(i, j, Color.PINK);
                    sc.refresh();
                }
                c -= 2;
            }
            for (int i = pr+n/2; i <= pr + n - 1; i++)
            {
                for (int j = pb; j <= pr + c/2; j++)
                {
                    sc.print(i, j, Color.PINK);
                    sc.refresh();
                }
                c += 2;
            }
        }
    }

    
}
    
