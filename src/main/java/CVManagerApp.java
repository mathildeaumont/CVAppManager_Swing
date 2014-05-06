import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import resume.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static javax.swing.ScrollPaneConstants.*;

/**
 * Created by Mathilde on 02/05/14.
 */
public class CVManagerApp {

    private Service service;
    private JAXBContext jc;
    private static final QName qname = new QName("", "");

    private static final int FRAME_WIDTH = 420;
    private static final int FRAME_HEIGHT = 600;
    private static final String url = "http://tpaumontxml.mathildeaumont.cloudbees.net/rest/resume";
    //private static final String url = "http://localhost:8080/TPAumontXML-1.0-SNAPSHOT/rest/resume";
    private JFrame frame;
    private JTextPane textArea;
    private JScrollPane scroll;


    private JButton seeCV;
    private JButton addCV;
    private JButton seeOk;
    private JTextField resumeId;

    private JButton addLanguages;
    private JButton addExperiencesProf;
    private JButton addExperiences;
    private JButton addSchools;
    private JButton addCompInf;
    private JButton aj;
    private JButton accueil;

    private JTextField nom;
    private JTextField prenom;
    private JTextField objectif;

    private List<JTextField> compInf;
    private List<JTextField> languages;
    private List<JTextField> experiences;
    private List<JTextField> experiencesProf;
    private List<JTextField> schools;

    public CVManagerApp() {
        try {
            jc = JAXBContext.newInstance(Resume.class, ResumeManager.class, ComputerSkill.class, Language.class, Experience.class, ProfessionalExperience.class,
                    School.class, ComputerSkillManager.class, LanguageManager.class, ExperienceManager.class, ProfessionalExperienceManager.class,
                    SchoolManager.class, ComputerSkillManager.class
            );
        } catch (JAXBException je) {
            System.out.println("Cannot create JAXBContext " + je);
        }
        createView();
        placeComponents();
        createController();
    }

    private void createView() {

        frame = new JFrame("CVManager");
        frame.setBackground(Color.BLACK);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        textArea = new JTextPane();
        textArea.setEditable(false);

        scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        java.net.URL imgUrl2 = getClass().getResource("bouton_voir_cv.png");
        ImageIcon icon2 = new ImageIcon(imgUrl2);
        seeCV = new JButton(icon2);
        seeCV.setBackground(Color.BLACK);
        seeCV.setForeground(Color.BLACK);
        seeCV.setBorder(null);

        java.net.URL imgUrl3 = getClass().getResource("bouton_ajoute_cv.png");
        ImageIcon icon3 = new ImageIcon(imgUrl3);
        addCV = new JButton(icon3);
        addCV.setBackground(Color.BLACK);
        addCV.setForeground(Color.BLACK);
        addCV.setBorder(null);

        java.net.URL ok = getClass().getResource("ok.png");
        ImageIcon iconOK = new ImageIcon(ok);
        seeOk = new JButton(iconOK);
        seeOk.setBackground(Color.BLACK);
        seeOk.setForeground(Color.BLACK);
        seeOk.setBorder(null);

        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);

        resumeId = new JTextField(2);

        nom = new JTextField();
        prenom = new JTextField();
        objectif = new JTextField();

        java.net.URL plus = getClass().getResource("plus.png");
        ImageIcon iconPlus = new ImageIcon(plus);

        addCompInf = new JButton(iconPlus);
        addLanguages = new JButton(iconPlus);
        addSchools = new JButton(iconPlus);
        addExperiences = new JButton(iconPlus);
        addExperiencesProf = new JButton(iconPlus);

        addCompInf.setForeground(Color.BLACK);
        addCompInf.setBackground(Color.BLACK);
        addCompInf.setBorder(null);
        addSchools.setForeground(Color.BLACK);
        addSchools.setBackground(Color.BLACK);
        addSchools.setBorder(null);
        addLanguages.setForeground(Color.BLACK);
        addLanguages.setBackground(Color.BLACK);
        addLanguages.setBorder(null);
        addExperiencesProf.setForeground(Color.BLACK);
        addExperiencesProf.setBackground(Color.BLACK);
        addExperiencesProf.setBorder(null);
        addExperiences.setForeground(Color.BLACK);
        addExperiences.setBackground(Color.BLACK);
        addExperiences.setBorder(null);


        compInf = new ArrayList<JTextField>();
        compInf.add(new JTextField("a"));
        compInf.add(new JTextField("a"));

        languages = new ArrayList<JTextField>();
        languages.add(new JTextField("a"));
        languages.add(new JTextField("a"));

        schools = new ArrayList<JTextField>();
        schools.add(new JTextField("a"));
        schools.add(new JTextField("a"));
        schools.add(new JTextField("a"));

        experiences = new ArrayList<JTextField>();
        experiences.add(new JTextField("a"));
        experiences.add(new JTextField("a"));
        experiences.add(new JTextField("a"));

        experiencesProf = new ArrayList<JTextField>();
        experiencesProf.add(new JTextField("a"));
        experiencesProf.add(new JTextField("a"));
        experiencesProf.add(new JTextField("a"));

        java.net.URL ajouter = getClass().getResource("ajouter.png");
        ImageIcon iconAjouter = new ImageIcon(ajouter);

        aj = new JButton(iconAjouter);
        aj.setForeground(Color.BLACK);
        aj.setBackground(Color.BLACK);
        aj.setBorder(null);

        java.net.URL acc = getClass().getResource("accueil.png");
        ImageIcon iconAccueil = new ImageIcon(acc);

        accueil = new JButton(iconAccueil);
        accueil.setForeground(Color.BLACK);
        accueil.setBackground(Color.BLACK);
        accueil.setBorder(null);
    }

    private void placeComponents() {

        JPanel panel = new JPanel(new GridLayout(0, 1)); {
            java.net.URL imgUrl = getClass().getResource("home.png");
            ImageIcon icon = new ImageIcon(imgUrl);
            JLabel lab = new JLabel(icon);
            panel.add(lab, BorderLayout.CENTER);
            panel.setBackground(Color.BLACK);
            JPanel p2 = new JPanel();

            p2.add(seeCV, BorderLayout.CENTER);

            p2.setBackground(Color.BLACK);
            panel.add(p2);
            JPanel p3 = new JPanel();
            p3.setBackground(Color.BLACK);
            p3.add(addCV, BorderLayout.CENTER);
            panel.add(p3);

        }
        frame.add(panel, BorderLayout.CENTER);
        frame.add(scroll, BorderLayout.CENTER);
        scroll.getViewport().add(panel);
    }

    private void allResumesView() {

        JPanel panel = new JPanel(new BorderLayout()); {
            JPanel panel2 = new JPanel(new BorderLayout()); {
                java.net.URL imgUrl = getClass().getResource("home.png");
                ImageIcon icon = new ImageIcon(imgUrl);
                JLabel lab = new JLabel(icon, JLabel.CENTER);
                lab.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                panel2.add(lab, BorderLayout.NORTH);
                panel2.setBackground(Color.BLACK);

                JPanel panel3 = new JPanel(new BorderLayout()); {
                    java.net.URL imgAfficher = getClass().getResource("afficher.png");
                    ImageIcon icon2 = new ImageIcon(imgAfficher);
                    JLabel afficher = new JLabel(icon2, JLabel.CENTER);
                    panel3.setBackground(Color.BLACK);
                    panel3.add(afficher, BorderLayout.WEST);

                    JPanel panel4 = new JPanel(new FlowLayout()); {
                        panel4.add(resumeId);
                        panel4.setBackground(Color.BLACK);
                    }
                    panel3.add(panel4);

                    panel3.add(seeOk, BorderLayout.EAST);
                }
                panel2.add(panel3, BorderLayout.SOUTH);
            }
            panel.add(panel2, BorderLayout.NORTH);
            panel.setBackground(Color.BLACK);

            panel.add(textArea, BorderLayout.CENTER);
            panel.add(accueil, BorderLayout.SOUTH);
            accueil.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        }
        frame.add(panel, BorderLayout.CENTER);
        frame.add(scroll, BorderLayout.CENTER);
        scroll.getViewport().add(panel);
    }

    private void addResumeView() {

        JPanel panel = new JPanel(new BorderLayout()); {
            JPanel panel2 = new JPanel(new BorderLayout()); {
                java.net.URL imgUrl = getClass().getResource("home.png");
                ImageIcon icon = new ImageIcon(imgUrl);
                JLabel lab = new JLabel(icon, JLabel.CENTER);
                lab.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                panel2.add(lab, BorderLayout.NORTH);
                panel2.setBackground(Color.BLACK);

                JPanel panel3 = new JPanel(new GridLayout(0, 2));  {
                    panel3.add(new JLabel("Nom"));
                    panel3.add(nom);

                    panel3.add(new JLabel(""));
                    panel3.add(new JLabel(""));

                    panel3.add(new JLabel("Prénom"));
                    panel3.add(prenom);

                    panel3.add(new JLabel(""));
                    panel3.add(new JLabel(""));

                    panel3.add(new JLabel("Objectif"));
                    panel3.add(objectif);

                    panel3.add(new JLabel(""));
                    panel3.add(new JLabel(""));

                    panel3.add(new JLabel("Formation"));
                    panel3.add(addSchools);
                    addSchools.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                    int j = 0;
                    for (int i = 0; i < schools.size(); i++) {
                        JLabel diplome = new JLabel("Diplôme");
                        JLabel lieu = new JLabel("Lieu");
                        JLabel annee = new JLabel("Année");

                        if (j%3 == 0) {
                            diplome.setForeground(new Color(190, 51, 51));
                            lieu.setForeground(new Color(190, 51, 51));
                            annee.setForeground(new Color(190, 51, 51));
                        } else if (j%3 == 1) {
                            diplome.setForeground(new Color(149, 193, 29));
                            lieu.setForeground(new Color(149, 193, 29));
                            annee.setForeground(new Color(149, 193, 29));
                        } else {
                            diplome.setForeground(new Color(29, 173, 193));
                            lieu.setForeground(new Color(29, 173, 193));
                            annee.setForeground(new Color(29, 173, 193));
                        }

                        panel3.add(diplome);
                        panel3.add(schools.get(i));
                        i++;
                        panel3.add(lieu);
                        panel3.add(schools.get(i));
                        i++;
                        panel3.add(annee);
                        panel3.add(schools.get(i));
                        j++;
                    }

                    panel3.add(new JLabel(""));
                    panel3.add(new JLabel(""));

                    panel3.add(new JLabel("Experience professionelle"));
                    panel3.add(addExperiencesProf);
                    addExperiencesProf.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                    j = 0;
                    for (int i = 0; i < experiencesProf.size(); i++) {
                        JLabel societe = new JLabel("Société");
                        JLabel job = new JLabel("Travail effectué");
                        JLabel annee = new JLabel("Année");

                        if (j%3 == 0) {
                            societe.setForeground(new Color(190, 51, 51));
                            job.setForeground(new Color(190, 51, 51));
                            annee.setForeground(new Color(190, 51, 51));
                        } else if (j%3 == 1) {
                            societe.setForeground(new Color(149, 193, 29));
                            job.setForeground(new Color(149, 193, 29));
                            annee.setForeground(new Color(149, 193, 29));
                        } else {
                            societe.setForeground(new Color(29, 173, 193));
                            job.setForeground(new Color(29, 173, 193));
                            annee.setForeground(new Color(29, 173, 193));
                        }

                        panel3.add(societe);
                        panel3.add(experiencesProf.get(i));
                        i++;
                        panel3.add(job);
                        panel3.add(experiencesProf.get(i));
                        i++;
                        panel3.add(annee);
                        panel3.add(experiencesProf.get(i));
                        j++;
                    }

                    panel3.add(new JLabel(""));
                    panel3.add(new JLabel(""));

                    panel3.add(new JLabel("Experience"));
                    panel3.add(addExperiences);
                    addExperiences.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                    j = 0;
                    for (int i = 0; i < experiences.size(); i++) {
                        JLabel nom = new JLabel("Nom");
                        JLabel description = new JLabel("Description");
                        JLabel annee = new JLabel("Année");

                        if (j%3 == 0) {
                            nom.setForeground(new Color(190, 51, 51));
                            description.setForeground(new Color(190, 51, 51));
                            annee.setForeground(new Color(190, 51, 51));
                        } else if (j%3 == 1) {
                            nom.setForeground(new Color(149, 193, 29));
                            description.setForeground(new Color(149, 193, 29));
                            annee.setForeground(new Color(149, 193, 29));
                        } else {
                            nom.setForeground(new Color(29, 173, 193));
                            description.setForeground(new Color(29, 173, 193));
                            annee.setForeground(new Color(29, 173, 193));
                        }

                        panel3.add(nom);
                        panel3.add(experiences.get(i));
                        i++;
                        panel3.add(description);
                        panel3.add(experiences.get(i));
                        i++;
                        panel3.add(annee);
                        panel3.add(experiences.get(i));
                        j++;
                    }

                    panel3.add(new JLabel(""));
                    panel3.add(new JLabel(""));

                    panel3.add(new JLabel("Langues"));
                    panel3.add(addLanguages);
                    addLanguages.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                    j = 0;
                    for (int i = 0; i < languages.size(); i++) {
                        JLabel nom = new JLabel("Nom");
                        JLabel niveau = new JLabel("Niveau");

                        if (j%3 == 0) {
                            nom.setForeground(new Color(190, 51, 51));
                            niveau.setForeground(new Color(190, 51, 51));
                        } else if (j%3 == 1) {
                            nom.setForeground(new Color(149, 193, 29));
                            niveau.setForeground(new Color(149, 193, 29));
                        } else {
                            nom.setForeground(new Color(29, 173, 193));
                            niveau.setForeground(new Color(29, 173, 193));
                        }

                        panel3.add(nom);
                        panel3.add(languages.get(i));
                        i++;
                        panel3.add(niveau);
                        panel3.add(languages.get(i));
                        j++;
                    }

                    panel3.add(new JLabel(""));
                    panel3.add(new JLabel(""));

                    panel3.add(new JLabel("Compétences informatiques"));
                    panel3.add(addCompInf);
                    addCompInf.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                    j = 0;
                    for (int i = 0; i < compInf.size(); i++) {
                        JLabel nom = new JLabel("Nom");
                        JLabel niveau = new JLabel("Niveau");

                        if (j%3 == 0) {
                            nom.setForeground(new Color(190, 51, 51));
                            niveau.setForeground(new Color(190, 51, 51));
                        } else if (j%3 == 1) {
                            nom.setForeground(new Color(149, 193, 29));
                            niveau.setForeground(new Color(149, 193, 29));
                        } else {
                            nom.setForeground(new Color(29, 173, 193));
                            niveau.setForeground(new Color(29, 173, 193));
                        }

                        panel3.add(nom);
                        panel3.add(compInf.get(i));
                        i++;
                        panel3.add(niveau);
                        panel3.add(compInf.get(i));
                        j++;
                    }

                    panel3.setBackground(Color.BLACK);
                    panel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                }
                panel2.add(panel3, BorderLayout.SOUTH);
            }
            panel.add(panel2, BorderLayout.NORTH);
            panel.setBackground(Color.BLACK);

            //panel.add(textArea, BorderLayout.CENTER);

            JPanel buttons = new JPanel(new GridLayout(0, 2)); {
                buttons.add(aj);
                buttons.add(accueil);
                aj.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            }
            panel.add(buttons, BorderLayout.SOUTH);

        }
        scroll.getViewport().add(panel);
    }

    private void createController() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        accueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeComponents();
            }
        });

        seeCV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allResumesView();
                try {
                    getAllResumes();
                } catch (Exception e1) {

                }
            }
        });

        addCV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addResumeView();
                try {
                    //getAllResumes();
                } catch (Exception e1) {

                }
            }
        });

        seeOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allResumesView();
                int id = 1;
                if (resumeId.getText().equals("")) {
                    try {
                        getAllResumes();
                    } catch (Exception e1) {

                    }
                    return;
                }
                try {
                    id = Integer.valueOf(resumeId.getText());
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(frame, "L'id n'est pas valide !");
                    return;
                }

                if (id <= 0) {
                    JOptionPane.showMessageDialog(frame, "L'id doit être positif !");
                    return;
                }
                try {
                    getResume(id);
                } catch (Exception e1) {
                    return;
                }
            }
        });

        addCompInf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compInf.add(new JTextField());
                compInf.add(new JTextField());
                addResumeView();
            }
        });

        addLanguages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languages.add(new JTextField());
                languages.add(new JTextField());
                addResumeView();
            }
        });

        addExperiences.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                experiences.add(new JTextField());
                experiences.add(new JTextField());
                experiences.add(new JTextField());
                addResumeView();
            }
        });

        addExperiencesProf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                experiencesProf.add(new JTextField());
                experiencesProf.add(new JTextField());
                experiencesProf.add(new JTextField());
                addResumeView();
            }
        });

        addSchools.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schools.add(new JTextField());
                schools.add(new JTextField());
                schools.add(new JTextField());
                addResumeView();
            }
        });

        aj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = false;
                try {
                    b = addCV();
                } catch (JAXBException jaxbe) {
                    //
                }
                if (b == true) {
                    placeComponents();
                    compInf = new ArrayList<JTextField>();
                    compInf.add(new JTextField("a"));
                    compInf.add(new JTextField("a"));

                    languages = new ArrayList<JTextField>();
                    languages.add(new JTextField("a"));
                    languages.add(new JTextField("a"));

                    schools = new ArrayList<JTextField>();
                    schools.add(new JTextField("a"));
                    schools.add(new JTextField("a"));
                    schools.add(new JTextField("a"));

                    experiences = new ArrayList<JTextField>();
                    experiences.add(new JTextField("a"));
                    experiences.add(new JTextField("a"));
                    experiences.add(new JTextField("a"));

                    experiencesProf = new ArrayList<JTextField>();
                    experiencesProf.add(new JTextField("a"));
                    experiencesProf.add(new JTextField("a"));
                    experiencesProf.add(new JTextField("a"));
                }
            }
        });
    }

    private void getAllResumes() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
        }

        try {
            Document document = builder.parse(url);
            document.getDocumentElement().normalize();
            textArea.setText("");
            afficherListeCV(document);
        } catch (Exception e) {
        }
        textArea.setCaretPosition(0);
    }

    private void getResume(int id) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {

        }

        try {
            Document document = null;
            try {
                document = builder.parse(url + "/" + id);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Ce cv n'existe pas !");
                document = null;
            }
            document.getDocumentElement().normalize();
            textArea.setText("");
            afficherListeCV(document);
        } catch (Exception e) {
        }
        textArea.setCaretPosition(0);
    }

    private void afficherListeCV(Document document) throws BadLocationException {

        NodeList l = document.getElementsByTagName("resume");

        StyledDocument doc = (StyledDocument) textArea.getDocument();

        // Style pour les catégories
        Style category = textArea.addStyle("Categorie", null);
        StyleConstants.setForeground(category, new Color(190, 51, 51));
        StyleConstants.setAlignment(category, StyleConstants.ALIGN_CENTER);

        // Style pour les sous catégories
        Style sousCategory = textArea.addStyle("Sous categorie", null);
        StyleConstants.setForeground(sousCategory, new Color(149, 193, 29));
        StyleConstants.setAlignment(sousCategory, StyleConstants.ALIGN_CENTER);

        // Style pour les sous catégories
        Style text = textArea.addStyle("Text", null);
        StyleConstants.setForeground(text, new Color(29, 173, 193));
        StyleConstants.setAlignment(text, StyleConstants.ALIGN_CENTER);

        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        //
        SimpleAttributeSet style = new SimpleAttributeSet();
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);

        for (int i = 0; i < l.getLength(); i++) {

            doc.insertString(doc.getLength(), "__________________________________\n\n", sousCategory);

            NodeList nodeList = l.item(i).getChildNodes();

            doc.insertString(doc.getLength(), "#" + nodeList.item(0).getTextContent() + "\n", text);
            doc.insertString(doc.getLength(), "\nNOM \n", category);
            doc.insertString(doc.getLength(), nodeList.item(1).getTextContent() + "\n", text);
            doc.insertString(doc.getLength(), "\nPRENOM \n", category);
            doc.insertString(doc.getLength(), nodeList.item(2).getTextContent() + "\n", text);
            doc.insertString(doc.getLength(), "\nOBJECTIF \n", category);
            doc.insertString(doc.getLength(), nodeList.item(3).getTextContent() + "\n", text);

            NodeList ecoles = nodeList.item(4).getChildNodes();
            doc.insertString(doc.getLength(), "\nFORMATION\n", category);
            for (int j = 0; j < ecoles.getLength(); j++) {
                doc.insertString(doc.getLength(), "\nDIPLOME ", sousCategory);
                doc.insertString(doc.getLength(), ecoles.item(j).getChildNodes().item(0).getTextContent() + "\n", text);
                doc.insertString(doc.getLength(), "LIEU ", sousCategory);
                doc.insertString(doc.getLength(), ecoles.item(j).getChildNodes().item(1).getTextContent() + "\n", text);
                doc.insertString(doc.getLength(), "ANNEE ", sousCategory);
                doc.insertString(doc.getLength(), ecoles.item(j).getChildNodes().item(2).getTextContent() + "\n", text);
            }

            NodeList expProf = nodeList.item(5).getChildNodes();
            doc.insertString(doc.getLength(), "\nEXPERIENCE PROFESSIONELLE\n", category);
            for (int j = 0; j < expProf.getLength(); j++) {
                doc.insertString(doc.getLength(), "\nTRAVAIL EFFECTUE ", sousCategory);
                doc.insertString(doc.getLength(), expProf.item(j).getChildNodes().item(0).getTextContent() + "\n", text);
                doc.insertString(doc.getLength(), "SOCIETE ", sousCategory);
                doc.insertString(doc.getLength(), expProf.item(j).getChildNodes().item(1).getTextContent() + "\n", text);
                doc.insertString(doc.getLength(), "ANNEE ", sousCategory);
                doc.insertString(doc.getLength(), expProf.item(j).getChildNodes().item(2).getTextContent() + "\n", text);
            }

            NodeList exp = nodeList.item(6).getChildNodes();
            doc.insertString(doc.getLength(), "\nEXPERIENCE\n", category);
            for (int j = 0; j < exp.getLength(); j++) {
                doc.insertString(doc.getLength(), "\nDESCRIPTION ", sousCategory);
                doc.insertString(doc.getLength(), exp.item(j).getChildNodes().item(0).getTextContent() + "\n", text);
                doc.insertString(doc.getLength(), "NOM ", sousCategory);
                doc.insertString(doc.getLength(), exp.item(j).getChildNodes().item(1).getTextContent() + "\n", text);
                doc.insertString(doc.getLength(), "ANNEE ", sousCategory);
                doc.insertString(doc.getLength(), exp.item(j).getChildNodes().item(2).getTextContent() + "\n", text);
            }

            NodeList langues = nodeList.item(7).getChildNodes();
            doc.insertString(doc.getLength(), "\nLANGUES\n", category);
            for (int j = 0; j < langues.getLength(); j++) {
                doc.insertString(doc.getLength(), "\nNOM ", sousCategory);
                doc.insertString(doc.getLength(), langues.item(j).getChildNodes().item(1).getTextContent() + "\n", text);
                doc.insertString(doc.getLength(), "NIVEAU ", sousCategory);
                doc.insertString(doc.getLength(), langues.item(j).getChildNodes().item(0).getTextContent() + "\n", text);
            }

            NodeList compImp = nodeList.item(8).getChildNodes();
            doc.insertString(doc.getLength(), "\nCOMPETENCES INFORMATIQUES\n", category);
            for (int j = 0; j < langues.getLength(); j++) {
                doc.insertString(doc.getLength(), "\nNOM ", sousCategory);
                doc.insertString(doc.getLength(), compImp.item(j).getChildNodes().item(1).getTextContent() + "\n", text);
                doc.insertString(doc.getLength(), "NIVEAU ", sousCategory);
                doc.insertString(doc.getLength(), compImp.item(j).getChildNodes().item(0).getTextContent() + "\n", text);
            }
        }
    }

    private boolean addCV() throws JAXBException {
        service = Service.create(qname);
        service.addPort(qname, HTTPBinding.HTTP_BINDING, url);
        Dispatch<Source> dispatcher = service.createDispatch(qname,
                Source.class, Service.Mode.MESSAGE);
        Map<String, Object> requestContext = dispatcher.getRequestContext();
        requestContext.put(MessageContext.HTTP_REQUEST_METHOD, "PUT");

        boolean added = true;

        if (nom.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Le champ Nom doit être rempli !");
            return false;
        } else if (prenom.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Le champ Prénom doit être rempli !");
            return false;
        } else if (objectif.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Le champ Objectif doit être rempli !");
            return false;
        } else if (schools.get(0).getText().equals("") || schools.get(1).getText().equals("") || schools.get(2).getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Vous devez entrer au moins une formation !");
            return false;
        } else if (experiences.get(0).getText().equals("") || experiences.get(1).getText().equals("") || experiences.get(2).getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Vous devez entrer au moins une expérience !");
            return false;
        } else if (experiencesProf.get(0).getText().equals("") || experiencesProf.get(1).getText().equals("") || experiencesProf.get(2).getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Vous devez entrer au moins une expérience professionnelle !");
            return false;
        } else if (compInf.get(0).getText().equals("") || compInf.get(1).getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Vous devez entrer au moins une compétence informatique !");
            return false;
        } else if (languages.get(0).getText().equals("") || languages.get(1).getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Vous devez entrer au moins une langue !");
            return false;
        } else {


            Resume resume = new Resume();
            resume.setLastName(nom.getText());
            resume.setFirstName(prenom.getText());
            resume.setObjectif(objectif.getText());

            LanguageManager lm = new LanguageManager();
            for (int i = 0; i < languages.size(); i++) {
                String name = languages.get(i).getText(); i++;
                String level = languages.get(i).getText();
                if (!name.equals("") && !level.equals("")) {
                    Language lang = new Language(name, level);
                    lm.addLanguage(lang);
                } else if (name.equals("") && level.equals("")) {
                } else {
                    JOptionPane.showMessageDialog(frame, "Les 2 champs des langues sont obligatoires !");
                    added = false;
                    return false;
                }
            }
            resume.setLanguages(lm);

            ComputerSkillManager cm = new ComputerSkillManager();
            for (int i = 0; i < compInf.size(); i++) {
                String name = compInf.get(i).getText(); i++;
                String level = compInf.get(i).getText();
                if (!name.equals("") && !level.equals("")) {
                    ComputerSkill cs = new ComputerSkill(name, level);
                    cm.addComputerSkill(cs);
                } else if (name.equals("") && level.equals("")) {
                } else {
                    JOptionPane.showMessageDialog(frame, "Les 2 champs des compétences informatiques sont obligatoires !");
                    added = false;
                    return false;
                }
            }
            resume.setComputerSkills(cm);

            ExperienceManager em = new ExperienceManager();
            for (int i = 0; i < experiences.size(); i++) {
                String name = experiences.get(i).getText(); i++;
                String description = experiences.get(i).getText(); i++;
                String year = experiences.get(i).getText();
                if (!name.equals("") && !description.equals("") && !year.equals("")) {
                    Experience ex = new Experience(name, description, year);
                    em.addExperience(ex);
                } else if (name.equals("") && description.equals("") && year.equals("")) {
                } else {
                    JOptionPane.showMessageDialog(frame, "Les 3 champs des expériences sont obligatoires !");
                    added = false;
                    return false;
                }
            }
            resume.setExperiences(em);

            ProfessionalExperienceManager pem = new ProfessionalExperienceManager();
            for (int i = 0; i < experiencesProf.size(); i++) {
                String society = experiencesProf.get(i).getText(); i++;
                String year = experiencesProf.get(i).getText(); i++;
                String job = experiencesProf.get(i).getText();
                if (!society.equals("") && !year.equals("") && !job.equals("")) {
                    ProfessionalExperience pex = new ProfessionalExperience(society, year, job);
                    pem.addProfessionnalExperience(pex);
                } else if (society.equals("") && year.equals("") && job.equals("")) {
                } else {
                    JOptionPane.showMessageDialog(frame, "Les 3 champs des expériences professionnelles sont obligatoires !");
                    added = false;
                    return false;
                }
            }
            resume.setProfessionalExperiences(pem);

            SchoolManager sm = new SchoolManager();
            for (int i = 0; i < schools.size(); i++) {
                String name = schools.get(i).getText(); i++;
                String diplom = schools.get(i).getText(); i++;
                String year = schools.get(i).getText();
                if (!name.equals("") && !diplom.equals("") && !year.equals("")) {
                    School sc = new School(name, diplom, year);
                    sm.addSchool(sc);
                } else if (name.equals("") && diplom.equals("") && year.equals("")) {
                } else {
                    JOptionPane.showMessageDialog(frame, "Les 3 champs des formations sont obligatoires !");
                    added = false;
                    return false;
                }
            }
            resume.setSchools(sm);

            try {
                dispatcher.invoke(new JAXBSource(jc, resume));
            } catch (Exception e) {
                //
            }

            if (added == true) {
                JOptionPane.showMessageDialog(frame, "CV ajouté !");
            }
            return true;
        }
    }

    public void display() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CVManagerApp().display();
            }
        });
    }
}
