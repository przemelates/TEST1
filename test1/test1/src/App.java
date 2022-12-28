
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;

 
class opquestion extends question {
  JFrame frame = new JFrame();
  JPanel panel = new JPanel();
  JButton button = new JButton("Press to continue");
  ArrayList <String> correct = new ArrayList <String>();
  ArrayList <JTextField> txt = new ArrayList <JTextField>();
  ArrayList <String> usersanswers = new ArrayList <String> ();
  opquestion(String c, String cor )
  {
    frame.add(panel);
    panel.setLayout(new BoxLayout (panel,BoxLayout.PAGE_AXIS));
    panel.add(button);
    button.addActionListener(this);
    frame.setSize(500,500);
    content = c;
    JLabel lab = new JLabel("\n");
    panel.add(lab);
    ArrayList <Integer> space = new ArrayList <Integer> ();
    space.add(0);
    StringBuilder st = new StringBuilder (c);
    for(int i=0;i<c.length();i++)
    {
      if(c.charAt(i)==';')
      {
        st.setCharAt(i, ' ');
        String s = st.substring(space.get(space.size()-1),i);
        space.add(i);
        JLabel label = new JLabel (s+"\n");
        panel.add(label);
      }

    }
    for(int i=0;i<c.length();i++)
    {
      if(c.charAt(i)==')')
      {
        JTextField t = new JTextField("Type in your answer" + " " + (txt.size()+1));
        txt.add(t);
        panel.add(t);
      }
    }
    StringBuilder sb = new StringBuilder(cor);
    int semicolon = 0;
    for(int i=0;i<cor.length();i++)
    {
      if(cor.charAt(i)==';')
      {
        if(semicolon==0)
        {
          String y = sb.substring(semicolon,i);
          correct.add(y);
          semicolon = i;
        }
        else
        {
        String x = sb.substring(semicolon+1,i);
        correct.add(x);
        semicolon = i;
        }
      }
    }
   
    frame.setVisible(true);
    do
    {
      try
      {
        Thread.sleep(1000);
      }
      catch(InterruptedException e)
      {

      }
    }
    while(button.isSelected()==false);
 


  }
  public void actionPerformed (ActionEvent e)
  {
    button.setSelected(true);
    for(int i=0;i<txt.size();i++)
    {
      String s = txt.get(i).getText();
      usersanswers.add(s);
    }
    for(int i=0;i<usersanswers.size();i++)
    {
    

      if(correct.get(i).equalsIgnoreCase(usersanswers.get(i)))
      {
        count ++;
    
      }
    }
    frame.setVisible(false);
    completed+=usersanswers.size();
    qcount +=usersanswers.size();
  }
}
 class question implements ActionListener {

   
    static int qcount = 0;
    static int completed = 0;
    String content;
    String[] answers = {"A","B","C","D","E"};
    int id;
    int[] correct = new int[5];
    boolean point;
    public static int count =0;
    JCheckBox rad [] = new JCheckBox[5];
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton button = new JButton("Press to continue");
    static void qcount(int c)
    {
      qcount +=c;
      
    }
    void create(String c, int idd, int [] co, String [] a)
    {
      content = c;
      id = idd;
      correct = co;
      for(int i=0;i<5;i++)
      {
        answers[i] = a[i];
      }
    }
    void ask()
    {
        frame = new JFrame("Question" + (completed+1));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        panel = new JPanel();
        panel.setLayout(new BoxLayout (panel,BoxLayout.PAGE_AXIS));
        frame.add(panel); 
        JLabel label0 = new JLabel("\n");
        panel.add(label0);
        StringBuilder sb = new StringBuilder(content);
        ArrayList <Integer> slash = new ArrayList<Integer>();
        for(int i=0;i<content.length();i++)
        {
         
            if(content.charAt(i)=='\\')
            {
               sb.setCharAt(i, ' ');
               content = sb.toString();
               slash.add(i);
            }
          
        }
        slash.add(content.length());
        if(slash.size() ==1)
        {
          JLabel label = new JLabel(content);
          panel.add(label);
        }
        else
        {
           String cont1 = sb.substring(0,slash.get(0));
           JLabel label1 = new JLabel(cont1);
           panel.add(label1);
           for(int i=0;i<slash.size()-1;i++)
           {
             String cont = sb.substring(slash.get(i),slash.get(i+1));
             JLabel labeli = new JLabel(cont);
             panel.add(labeli);
           }
        }
        
        JLabel label3 = new JLabel("\n");
        panel.add(label3);
        JLabel label2 = new JLabel("Options:");
        panel.add(label2);
        frame.setSize(500,500);

        
        for(int i=0;i<answers.length;i++)
        {
            rad[i] = new JCheckBox(answers[i]);
            rad[i].setBounds(10*i+10, 10*i+10, 20, 20);
            panel.add(rad[i]);
            
        }
        
        panel.add(button);
        button.addActionListener(this);
        frame.setVisible(true);
        
        do
        {
          try
          {
            Thread.sleep(1000);
          }
          catch(InterruptedException e)
          {

          }
        }
        while(button.isSelected()==false);
        

        
        
    }
    public void actionPerformed(ActionEvent e )
    {
      int score =0;
      button.setSelected(true);
      for (int i=0;i<5;i++)
        {
          if((rad[i].isSelected() == true && 1 == correct[i])||(rad[i].isSelected() == false && 0 == correct[i]))
          {
            score++;

          }
          
        }
        if(score==5)
        {
          count++;
        }
        completed++;
        if(completed==qcount)
        {
        String nivel ="";
        double res = (double)count/(double)completed;
        res*=100;
        res =Math.ceil(res);
        if(res<20)
        {
          nivel = "No ha superado ningún nivel. ¡Estudie mas!";
        }
        if(res>=20&&res<35)
        {
          nivel = "Nivel estimado: A1";
        }
        if(res>=35&&res<50)
        {
          nivel = "Nivel estimado: A2";
        }
        if(res>=50&&res<65)
        {
          nivel = "Nivel estimado: B1";
        }
        if(res>=65&&res<85)
        {
          nivel = "Nivel estimado: B2";
        }
        if(res>=85&&res<100)
        {
          nivel = "Nivel estimado: C1";
        }
        if(res==100)
        {
          nivel = "Nivel estimado: C2" + '\n' + "Usted es un gran maestro de esta lengua. ¡Enhorabuena!";
        }
        

        Frame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Resultado:"+res + '%' +'\n' + nivel);
        while(true)
        {
          if(f.isShowing()==false)
        {
          System.exit(0);
        }


        }
        }
        frame.setVisible(false);
        
     
        
      
    }
    
}
public class App {
    public static void main(String[] args) throws Exception {
        String ocontents = new String();
        String ocorrect = new String();
        URL urlo1 = App.class.getResource("contentso.txt");
        URL urlo2 = App.class.getResource ("corro.txt");
        Scanner reado = new Scanner(urlo1.openStream());
        while(reado.hasNext())
        {
          ocontents+=reado.nextLine();
        }
        reado.close();
        Scanner reado2 = new Scanner(urlo2.openStream());
        while(reado2.hasNext())
        {
          ocorrect+= reado2.nextLine();
        }
        reado2.close();
        opquestion opquestion = new opquestion(ocontents,ocorrect);

        ArrayList<String> contents = new ArrayList<String>();
        URL url1 = App.class.getResource("contents2.txt");
        URL url2 = App.class.getResource("answers2.txt");
        URL url3 = App.class.getResource("corr.txt");

        Scanner read = new Scanner( url1.openStream());
        read.useDelimiter(";");
        while (read.hasNext())
        {
          contents.add(read.next());
        }
       
        read.close();
        String answers [][] = new String [contents.size()][5];
        Scanner read2 = new Scanner(url2.openStream());
        read2.useDelimiter(";");
        for(int j=0;j<contents.size();j++)
        {
          for(int i=0;i<5;i++)
          {
          answers[j][i] = read2.next();
          }
        
        }
        read2.close();
        int [][] correct_answers = new int[contents.size()][5];
        Scanner read3 = new Scanner(url3.openStream());
        for(int j=0;j<contents.size();j++)
        {
          for(int i=0;i<5;i++)
          {
            correct_answers[j][i] = read3.nextInt();
          }
        }
        read3.close();
        question.qcount(contents.size());
        for(int i=0;i<contents.size();i++)
        {
          String [] ans = new String[5];
          for (int j=0;j<5;j++)
          {
            int asc = j+65;
            char abcd = (char) asc;
            ans[j] = abcd + ": " + answers[i][j] ;
            
          }
          question Question = new question();
          Question.create(contents.get(i),i,correct_answers[i],ans);
          Question.ask();
          
          
        }
        
        
        
        
        
        
    }
}

