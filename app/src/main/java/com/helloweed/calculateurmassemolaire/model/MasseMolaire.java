package com.helloweed.calculateurmassemolaire.model;

import java.util.Objects;

public class MasseMolaire {
    String[][] mtableau;

    boolean mentreparenthese;
    double[][] mmN = new double[101][3];
    double[][] mmNa = new double[101][3];
    double[][] mmPa = new double[101][3];
    double[][] mmP = new double[101][3];
    double mmasse;
    boolean[] marqueur = new boolean[119];

    double mmassea;
    int mnbc;
    int mb;
    int mm;
    int mn;
    String mIndex;
    String[] mlettre = new String[5];


    public void setReinitialisation() {
        mmasse = 0;
        mmassea = 0;

    }

    static double arrondi(double pNombre) {
        double nb;
        nb = (int) (pNombre * 10000);
        nb = nb / 10000;
        return nb;
    }

    static String subStringPerso(String pchainDeChar, int pindexChar, int pnombreChar) {
        int longueurChaine;
        int indexMax = pindexChar;
        String mChainDeChar = pchainDeChar;
        int mIndexChar = pindexChar - 1;
        int mNombreChar = pnombreChar;
        longueurChaine = pchainDeChar.length();
        indexMax = longueurChaine - 1;
        String valeur_a_retourner = "";
        if (mNombreChar == 1) {
            if (mIndexChar + 1 > longueurChaine) {
                valeur_a_retourner = "";
            } else {
                valeur_a_retourner = mChainDeChar.substring(mIndexChar, mIndexChar + 1);
            }
        }
        if (mNombreChar == 2) {
            if (mIndexChar + 2 > longueurChaine) {
                if (mIndexChar + 1 > longueurChaine) {
                    valeur_a_retourner = "";
                } else {
                    valeur_a_retourner = mChainDeChar.substring(mIndexChar, mIndexChar + 1);
                }

            } else {
                valeur_a_retourner = mChainDeChar.substring(mIndexChar, mIndexChar + 2);
            }
        }
        if (mNombreChar > 2) {
            if (mIndexChar + mNombreChar > longueurChaine) {
                if (mIndexChar + mNombreChar - 1 > longueurChaine) {
                    valeur_a_retourner = "";
                } else {
                    valeur_a_retourner = mChainDeChar.substring(mIndexChar, mIndexChar + mNombreChar - 1);
                }

            } else {
                valeur_a_retourner = mChainDeChar.substring(mIndexChar, mIndexChar + mNombreChar);
            }
        }
        return valeur_a_retourner;
    }

    public String quelsElements(String formuleBrute) {
        String listElements = new String();

        for (int n = 1; n <= 118; n++) {
            if (marqueur[n] == true) {
                listElements = "" + listElements + "" + mtableau[n][1] + " : " + mtableau[n][0] + " " + mtableau[n][2] + " g/mol" + "\n";
            }
        }
        return "\nElements présents : \n" + listElements;
    }

    public String quelsAtomes(String formuleBrute) {
        String listElements = new String();

        for (int n = 1; n <= 118; n++) {
            if (marqueur[n] == true) {
                listElements = "" + listElements + "" + mtableau[n][1] + " : " + mtableau[n][0] + "\n";
            }
        }
        return "\n" + listElements;
    }

    public String quelsMasses(String formuleBrute) {
        String listElements = new String();

        for (int n = 1; n <= 118; n++) {
            if (marqueur[n] == true) {
                listElements = "" + listElements + "" + mtableau[n][2] + "\n";
            }
        }
        return "\n" + listElements;
    }

    public String quellesUnites(String formuleBrute) {
        String listElements = new String();

        for (int n = 1; n <= 118; n++) {
            if (marqueur[n] == true) {
                listElements = "" + listElements + "g/mol \n";
            }
        }
        return "\n" + listElements;
    }


    public double calculMasseMolaire(String formuleBrute, String formuleBrute2) {
        for (int n = 1; n <= 118; n++) {
            marqueur[n] = false;
        }
        this.setReinitialisation();
        mentreparenthese = false;
        for (int n = 1; n <= 100; n++) {
            mmN[n][2] = 1;
            mmNa[n][2] = 1;
            mmPa[n][2] = 1;
            mmP[n][2] = 1;
            mmN[n][1] = 0;
            mmNa[n][1] = 0;
            mmPa[n][1] = 0;
            mmP[n][1] = 0;
        }
        mnbc = formuleBrute.length();
        System.out.println(formuleBrute);
        System.out.println("Nombre de caractère : " + mnbc);

        mb = 1;
        mm = 1;
        mn = 1;

        for (int a = 1; a <= mnbc; a++) {
            System.out.println("Numéro de caractère : " + a);
            System.out.println("Numero du groupe de masse : " + mm);
            System.out.println("Lettre Traitée : " + subStringPerso(formuleBrute, a, 1));
            mlettre[1] = subStringPerso(formuleBrute, a, 1);
            if (!Objects.equals(mlettre[1], ",") && !Objects.equals(mlettre[1], " ")) {
                System.out.println("Absence de , et d'espace");
                if (!mlettre[1].equals(")")) {
                    System.out.println("Absence de )");
                    if (!mlettre[1].chars().allMatch(Character::isDigit)) {
                        System.out.println("N'est pas numérique");
                        if (!Objects.equals(mlettre[1], "(")) {
                            System.out.println("Absence de (");
                            mlettre[2] = subStringPerso(formuleBrute, a + 1, mb);
                            System.out.println("Le second caractère est " + mlettre[2]);
                            if (!mlettre[2].chars().allMatch(Character::isDigit)) {
                                System.out.println("Le deuxième caractère n'est pas numérique");
                                if (!mlettre[2].equals(mlettre[2].toUpperCase())) {
                                    System.out.println("Le second caractère est une minuscule");
                                    mlettre[1] = subStringPerso(formuleBrute, a, 2);
                                    a = a + 1;
                                    for (int n = 1; n <= 118; n++) {
                                        if (Objects.equals(mlettre[1], mtableau[n][1])) {
                                            marqueur[n] = true;
                                            System.out.println("On place à true la valeur n = " + n + " du tableau ");
                                        }
                                    }
                                    mlettre[2] = subStringPerso(formuleBrute, a + 1, mb);
                                }
                                System.out.println("Le second caractère est une majuscule");
                                for (int n = 1; n <= 118; n++) {
                                    if (Objects.equals(mlettre[1], mtableau[n][1])) {
                                        marqueur[n] = true;
                                        System.out.println("On place à true la valeur n = " + n + " du tableau ");
                                        break;
                                    }
                                }
                            }
                            if (mlettre[2].chars().allMatch(Character::isDigit) && mlettre[2] != "") {                         //C'est là
                                while (subStringPerso(formuleBrute, a + 1, mb).chars().allMatch(Character::isDigit) && !subStringPerso(formuleBrute, a + mb, 1).equals("") && !subStringPerso(formuleBrute, a + mb, 1).equals(",") && !subStringPerso(formuleBrute, a + mb, 1).equals(")")) {
                                    System.out.println(subStringPerso(formuleBrute, a + 1, mb));
                                    System.out.println(subStringPerso(formuleBrute, a + mb, 1));
                                    mb = mb + 1;
                                }
                                mb = mb - 1;
                                System.out.println(subStringPerso(formuleBrute, a + 1, mb));
                                mlettre[2] = subStringPerso(formuleBrute, a + 1, mb);
                                System.out.println(mlettre[2] + " entités à prendre en compte");
                                a = a + mb + 1;
                                mb = 1;
                            } else {
                                System.out.println("Le second caracère n'est pas numérique");
                                mlettre[2] = String.valueOf(1);
                                System.out.println("Une seule entité à prendre en compte");
                                a = a + 1;
                                System.out.println("a=a+1");
                                // Ou là !
                            }
                            mIndex = String.valueOf(0);
                            System.out.println("Index = 0");
                            for (int c = 0; c <= 118; c++) {
                                if (Objects.equals(mlettre[1], mtableau[c][1])) {
                                    mIndex = mtableau[c][2];
                                }
                            }
                            if (!mentreparenthese) {

                                for (int n = 1; n <= 118; n++) {
                                    if (Objects.equals(mlettre[1], mtableau[n][1])) {
                                        marqueur[n] = true;
                                        System.out.println("On place à true la valeur n = " + n + " du tableau ");
                                    }
                                }
                                System.out.println("La variable parenthèse est fausse");
                                mmN[mm][1] = mmN[mm][1] + Double.parseDouble(mIndex) * Double.parseDouble(mlettre[2]);
                                System.out.println("Masse : " + mmN[mm][1]);
                                if (formuleBrute2 == mlettre[1]) {
                                    mmNa[mm][1] = mmNa[mm][1] + Double.parseDouble(mIndex) * Double.parseDouble(mlettre[2]);
                                }
                            } else {
                                mmP[mn][1] = mmP[mn][1] + Double.parseDouble(mIndex) * Double.parseDouble(mlettre[2]);

                                if (formuleBrute2 == mlettre[1]) {
                                    mmPa[mn][1] = mmPa[mn][1] + Double.parseDouble(mIndex) * Double.parseDouble(mlettre[2]);
                                }
                            }
                        } else {
                            mentreparenthese = true;
                            a = a + 1;
                            mn = mn + 1;
                        }
                    } else {
                        while (subStringPerso(formuleBrute, a, mb).chars().allMatch(Character::isDigit) == true && !subStringPerso(formuleBrute, a - 1 + mb, 1).equals("") && !subStringPerso(formuleBrute, a - 1 + mb, 1).equals(",")) {
                            mb = mb + 1;
                        }
                        mb = mb - 1;
                        mlettre[1] = subStringPerso(formuleBrute, a, mb);
                        a = a + mb;
                        mb = 1;
                        mmN[mm][2] = Double.parseDouble(mlettre[1]);
                        mmNa[mm][2] = Double.parseDouble(mlettre[1]);
                    }
                } else {
                    mlettre[2] = subStringPerso(formuleBrute, a + 1, mb);
                    while (subStringPerso(formuleBrute, a + 1, mb).chars().allMatch(Character::isDigit) == true && !subStringPerso(formuleBrute, a + mb, 1).equals("") && !subStringPerso(formuleBrute, a + mb, 1).equals(",")) {
                        //'MsgBox Mid(FormuleBrute, a + 1, b)
                        mb = mb + 1;
                    }
                    mb = mb - 1;
                    mlettre[2] = subStringPerso(formuleBrute, a + 1, mb);
                    System.out.println("Le second caractère est " + mlettre[2]);
                    if (mlettre[2].chars().allMatch(Character::isDigit) == true && mlettre[2] != "") {
                        a = a + mb + 1;
                    } else {
                        mlettre[2] = String.valueOf(1);
                        a = a + 1;
                    }
                    mb = 1;
                    mmP[mn][2] = Double.parseDouble(mlettre[2]);
                    mmN[mm][1] = mmN[mm][1] + mmP[mn][1] * mmP[mn][2];
                    mmPa[mn][2] = Double.parseDouble(mlettre[2]);
                    mmNa[mm][1] = mmNa[mm][1] + mmPa[mn][1] * mmPa[mn][2];
                    mentreparenthese = false;
                }
            } else {
                a = a + 1;
                mm = mm + 1;

            }
            System.out.println("a=a-1");
            a = a - 1;
        }


        for (int i = 1; i <= 100; i++) {
            mmasse = mmN[i][1] * mmN[i][2] + mmasse;
            mmassea = mmNa[i][1] * mmNa[i][2] + mmassea;
        }


        if (formuleBrute2 == "") {

            System.out.println("Masse : " + mmasse);
            return arrondi(mmasse);
        } else {
            return (mmassea / mmasse) * 100;
        }
        //End Function


        // ET LA !


    }


    public void initialisationTableau() {
        mtableau = new String[130][3];
        mtableau[1][1] = "H";
        mtableau[2][1] = "C";
        mtableau[3][1] = "O";
        mtableau[4][1] = "Na";
        mtableau[5][1] = "S";
        mtableau[6][1] = "N";
        mtableau[7][1] = "K";
        mtableau[8][1] = "Ag";
        mtableau[9][1] = "Au";
        mtableau[10][1] = "He";
        mtableau[11][1] = "Li";
        mtableau[12][1] = "Be";
        mtableau[13][1] = "B";
        mtableau[14][1] = "F";
        mtableau[15][1] = "Ne";
        mtableau[16][1] = "Mg";
        mtableau[17][1] = "Al";
        mtableau[18][1] = "Si";
        mtableau[19][1] = "P";
        mtableau[20][1] = "Cl";
        mtableau[21][1] = "Ar";
        mtableau[22][1] = "Ca";
        mtableau[23][1] = "Sc";
        mtableau[24][1] = "Ti";
        mtableau[25][1] = "V";
        mtableau[26][1] = "Cr";
        mtableau[27][1] = "Mn";
        mtableau[28][1] = "Fe";
        mtableau[29][1] = "Co";
        mtableau[30][1] = "Ni";
        mtableau[31][1] = "Cu";
        mtableau[32][1] = "Zn";
        mtableau[33][1] = "Ga";
        mtableau[34][1] = "Ge";
        mtableau[35][1] = "As";
        mtableau[36][1] = "Se";
        mtableau[37][1] = "Br";
        mtableau[38][1] = "Kr";
        mtableau[39][1] = "Rb";
        mtableau[40][1] = "Sr";
        mtableau[41][1] = "Y";
        mtableau[42][1] = "Zr";
        mtableau[43][1] = "Nb";
        mtableau[44][1] = "Mo";
        mtableau[45][1] = "Tc";
        mtableau[46][1] = "Ru";
        mtableau[47][1] = "Rh";
        mtableau[48][1] = "Pd";
        mtableau[49][1] = "Cd";
        mtableau[50][1] = "In";
        mtableau[51][1] = "Sn";
        mtableau[52][1] = "Sb";
        mtableau[53][1] = "I";
        mtableau[54][1] = "Te";
        mtableau[55][1] = "Xe";
        mtableau[56][1] = "Cs";
        mtableau[57][1] = "Ba";
        mtableau[58][1] = "La";
        mtableau[59][1] = "Ce";
        mtableau[60][1] = "Pr";
        mtableau[61][1] = "Nd";
        mtableau[62][1] = "Pm";
        mtableau[63][1] = "Sm";
        mtableau[64][1] = "Eu";
        mtableau[65][1] = "Gd";
        mtableau[66][1] = "Tb";
        mtableau[67][1] = "Dy";
        mtableau[68][1] = "Ho";
        mtableau[69][1] = "Er";
        mtableau[70][1] = "Tm";
        mtableau[71][1] = "Yb";
        mtableau[72][1] = "Lu";
        mtableau[73][1] = "Hf";
        mtableau[74][1] = "Ta";
        mtableau[75][1] = "W";
        mtableau[76][1] = "Re";
        mtableau[77][1] = "Os";
        mtableau[78][1] = "Ir";
        mtableau[79][1] = "Pt";
        mtableau[80][1] = "Hg";
        mtableau[81][1] = "Tl";
        mtableau[82][1] = "Pb";
        mtableau[83][1] = "Bi";
        mtableau[84][1] = "Po";
        mtableau[85][1] = "At";
        mtableau[86][1] = "Rn";
        mtableau[87][1] = "Fr";
        mtableau[88][1] = "Ra";
        mtableau[89][1] = "Ac";
        mtableau[90][1] = "Pa";
        mtableau[91][1] = "Th";
        mtableau[92][1] = "Np";
        mtableau[93][1] = "U";
        mtableau[94][1] = "Am";
        mtableau[95][1] = "Pu";
        mtableau[96][1] = "Bk";
        mtableau[97][1] = "Cm";
        mtableau[98][1] = "Cf";
        mtableau[99][1] = "Es";
        mtableau[100][1] = "Fm";
        mtableau[101][1] = "Md";
        mtableau[102][1] = "No";
        mtableau[103][1] = "Lr";
        mtableau[104][1] = "Rf";
        mtableau[105][1] = "Db";
        mtableau[106][1] = "Bh";
        mtableau[107][1] = "Sg";
        mtableau[108][1] = "Hs";
        mtableau[109][1] = "Mt";
        mtableau[110][1] = "Ds";
        mtableau[111][1] = "Rg";
        mtableau[112][1] = "Cn";
        mtableau[113][1] = "Nh";
        mtableau[114][1] = "Fl";
        mtableau[115][1] = "Lv";
        mtableau[116][1] = "Mc";
        mtableau[117][1] = "Og";
        mtableau[118][1] = "Ts";

        mtableau[1][0] = "Hydrogène";
        mtableau[2][0] = "Carbone";
        mtableau[3][0] = "Oxygene";
        mtableau[4][0] = "Sodium";
        mtableau[5][0] = "Souffre";
        mtableau[6][0] = "Azote";
        mtableau[7][0] = "Potassium";
        mtableau[8][0] = "Argent";
        mtableau[9][0] = "Or";
        mtableau[10][0] = "Helium";
        mtableau[11][0] = "Lithium";
        mtableau[12][0] = "Beryllium";
        mtableau[13][0] = "Bore";
        mtableau[14][0] = "Fluor";
        mtableau[15][0] = "Néon";
        mtableau[16][0] = "Magnésium";
        mtableau[17][0] = "Aluminium";
        mtableau[18][0] = "Silicium";
        mtableau[19][0] = "Phosphore";
        mtableau[20][0] = "Chlore";
        mtableau[21][0] = "Argon";
        mtableau[22][0] = "Calcium";
        mtableau[23][0] = "Scandium";
        mtableau[24][0] = "Titane";
        mtableau[25][0] = "Vanadium";
        mtableau[26][0] = "Chrome";
        mtableau[27][0] = "Manganèse";
        mtableau[28][0] = "Fer";
        mtableau[29][0] = "Cobalt";
        mtableau[30][0] = "Nickel";
        mtableau[31][0] = "Cuivre";
        mtableau[32][0] = "Zinc";
        mtableau[33][0] = "Gallium";
        mtableau[34][0] = "Germanium";
        mtableau[35][0] = "Arsenic";
        mtableau[36][0] = "Sélénium";
        mtableau[37][0] = "Brome";
        mtableau[38][0] = "Krypton";
        mtableau[39][0] = "Rubidium";
        mtableau[40][0] = "Strontium";
        mtableau[41][0] = "Yttrium";
        mtableau[42][0] = "Zirconium";
        mtableau[43][0] = "Niobium";
        mtableau[44][0] = "Molybdène";
        mtableau[45][0] = "Technétium";
        mtableau[46][0] = "Ruthénium";
        mtableau[47][0] = "Rhodium";
        mtableau[48][0] = "Palladium";
        mtableau[49][0] = "Cadmium";
        mtableau[50][0] = "Indium";
        mtableau[51][0] = "Etain";
        mtableau[52][0] = "Antimoine";
        mtableau[53][0] = "Iode";
        mtableau[54][0] = "Tellure";
        mtableau[55][0] = "Xénon";
        mtableau[56][0] = "Césium";
        mtableau[57][0] = "Baryum";
        mtableau[58][0] = "Lanthane";
        mtableau[59][0] = "Cérium";
        mtableau[60][0] = "Praséodyme";
        mtableau[61][0] = "Néodyme";
        mtableau[62][0] = "Prométhium";
        mtableau[63][0] = "Samarium";
        mtableau[64][0] = "Europium";
        mtableau[65][0] = "Gadolinium";
        mtableau[66][0] = "Terbium";
        mtableau[67][0] = "Dysprosium";
        mtableau[68][0] = "Holmium";
        mtableau[69][0] = "Erbium";
        mtableau[70][0] = "Thulium";
        mtableau[71][0] = "Ytterbium";
        mtableau[72][0] = "Lutécium";
        mtableau[73][0] = "Hafnium";
        mtableau[74][0] = "Tantanle";
        mtableau[75][0] = "Tungstène";
        mtableau[76][0] = "Rhénium";
        mtableau[77][0] = "Osmium";
        mtableau[78][0] = "Iridium";
        mtableau[79][0] = "Platine";
        mtableau[80][0] = "Mercure";
        mtableau[81][0] = "Thallium";
        mtableau[82][0] = "Plomb";
        mtableau[83][0] = "Bismuth";
        mtableau[84][0] = "Polonium";
        mtableau[85][0] = "Astate";
        mtableau[86][0] = "Radon";
        mtableau[87][0] = "Francium";
        mtableau[88][0] = "Radium";
        mtableau[89][0] = "Actinium";
        mtableau[90][0] = "Protactinium";
        mtableau[91][0] = "Thorium";
        mtableau[92][0] = "Neptunium";
        mtableau[93][0] = "Uranium";
        mtableau[94][0] = "Américium";
        mtableau[95][0] = "Plutonium";
        mtableau[96][0] = "Berkélium";
        mtableau[97][0] = "Curium";
        mtableau[98][0] = "Californium";
        mtableau[99][0] = "Einsteinium";
        mtableau[100][0] = "Fermium";
        mtableau[101][0] = "Mendélévium";
        mtableau[102][0] = "Nobélium";
        mtableau[103][0] = "Lawrencium";
        mtableau[104][0] = "Rutherfordium";
        mtableau[105][0] = "Dubnium";
        mtableau[106][0] = "Seaborgium";
        mtableau[107][0] = "Bohrium";
        mtableau[108][0] = "Hassium";
        mtableau[109][0] = "Meitnérium";
        mtableau[110][0] = "Darmstadtium";
        mtableau[111][0] = "Roentgenium";
        mtableau[112][0] = "Copernicium";
        mtableau[113][0] = "Nihonium";
        mtableau[114][0] = "Flérovium";
        mtableau[115][0] = "Livermorium";
        mtableau[116][0] = "Moscovium";
        mtableau[117][0] = "Oganesson";
        mtableau[118][0] = "Tennesse";

        mtableau[1][2] = "1.00784";
        mtableau[2][2] = "12.0107";
        mtableau[3][2] = "15.999";
        mtableau[4][2] = "22.989769";
        mtableau[5][2] = "32.065";
        mtableau[6][2] = "14.0067";
        mtableau[7][2] = "39.0983";
        mtableau[8][2] = "107.8682";
        mtableau[9][2] = "196.96657";
        mtableau[10][2] = "4.0026";
        mtableau[11][2] = "6.9412";
        mtableau[12][2] = "9.0122";
        mtableau[13][2] = "10.811";
        mtableau[14][2] = "18.998";
        mtableau[15][2] = "20.18";
        mtableau[16][2] = "24.305";
        mtableau[17][2] = "26.982";
        mtableau[18][2] = "28.086";
        mtableau[19][2] = "30.974";
        mtableau[20][2] = "35.453";
        mtableau[21][2] = "39.948";
        mtableau[22][2] = "40.078";
        mtableau[23][2] = "44.956";
        mtableau[24][2] = "47.867";
        mtableau[25][2] = "50.942";
        mtableau[26][2] = "51.996";
        mtableau[27][2] = "54.938";
        mtableau[28][2] = "55.845";
        mtableau[29][2] = "58.933";
        mtableau[30][2] = "58.693";
        mtableau[31][2] = "63.546";
        mtableau[32][2] = "65.409";
        mtableau[33][2] = "69.723";
        mtableau[34][2] = "72.641";
        mtableau[35][2] = "74.922";
        mtableau[36][2] = "78.963";
        mtableau[37][2] = "79.904";
        mtableau[38][2] = "83.798";
        mtableau[39][2] = "85.467";
        mtableau[40][2] = "87.62";
        mtableau[41][2] = "88.90585";
        mtableau[42][2] = "91.224";
        mtableau[43][2] = "92.90638";
        mtableau[44][2] = "95.94";
        mtableau[45][2] = "98.9063";
        mtableau[46][2] = "101.07";
        mtableau[47][2] = "102.9055";
        mtableau[48][2] = "106.42";
        mtableau[49][2] = "112.411";
        mtableau[50][2] = "114.82";
        mtableau[51][2] = "118.71";
        mtableau[52][2] = "121.75";
        mtableau[53][2] = "126.90447";
        mtableau[54][2] = "127.6";
        mtableau[55][2] = "131.29";
        mtableau[56][2] = "132.90543";
        mtableau[57][2] = "137.327";
        mtableau[58][2] = "138.9055";
        mtableau[59][2] = "140.115";
        mtableau[60][2] = "140.90765";
        mtableau[61][2] = "144.24";
        mtableau[62][2] = "146.9151";
        mtableau[63][2] = "150.36";
        mtableau[64][2] = "151.965";
        mtableau[65][2] = "157.25";
        mtableau[66][2] = "158.92534";
        mtableau[67][2] = "162.5";
        mtableau[68][2] = "164.93032";
        mtableau[69][2] = "167.26";
        mtableau[70][2] = "168.93421";
        mtableau[71][2] = "173.04";
        mtableau[72][2] = "174.967";
        mtableau[73][2] = "178.49";
        mtableau[74][2] = "180.9479";
        mtableau[75][2] = "183.85";
        mtableau[76][2] = "186.207";
        mtableau[77][2] = "190.2";
        mtableau[78][2] = "192.22";
        mtableau[79][2] = "195.08";
        mtableau[80][2] = "200.59";
        mtableau[81][2] = "204.3833";
        mtableau[82][2] = "207.2";
        mtableau[83][2] = "208.98037";
        mtableau[84][2] = "208.9824";
        mtableau[85][2] = "209.9871";
        mtableau[86][2] = "222.0176";
        mtableau[87][2] = "223.0197";
        mtableau[88][2] = " 226.0254";
        mtableau[89][2] = "227.0278";
        mtableau[90][2] = "231.0359";
        mtableau[91][2] = "232.0381";
        mtableau[92][2] = "237.0482";
        mtableau[93][2] = "238.0289";
        mtableau[94][2] = "243.0614";
        mtableau[95][2] = "244.0642";
        mtableau[96][2] = "247.0703";
        mtableau[97][2] = "247.0703";
        mtableau[98][2] = "251.0796";
        mtableau[99][2] = "252.0829";
        mtableau[100][2] = "257.0951";
        mtableau[101][2] = "258.0986";
        mtableau[102][2] = "259.1009";
        mtableau[103][2] = "260.1053";
        mtableau[104][2] = "261.1087";
        mtableau[105][2] = "262.1138";
        mtableau[106][2] = "262.1229";
        mtableau[107][2] = "263.1182";
        mtableau[108][2] = "265";
        mtableau[109][2] = "266";
        mtableau[110][2] = "269";
        mtableau[111][2] = "272";
        mtableau[112][2] = "277";
        mtableau[113][2] = "286";
        mtableau[114][2] = "0";
        mtableau[115][2] = "0";
        mtableau[116][2] = "0";
        mtableau[117][2] = "0";
        mtableau[118][2] = "0";
    }


}
