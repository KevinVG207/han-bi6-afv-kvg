public class Gene implements Comparable<Gene> {

    private int gene_id;
    private String symbol;
    private String location;

    public String getSymbol() {
        return symbol;
    }

    public String getLocation() {
        return location;
    }

    public int getGene_id() {
        return gene_id;
    }

    public Gene(int gene_id, String symbol, String location) {
        this.gene_id = gene_id;
        this.symbol = symbol;
        this.location = location;
    }

    @Override
    public int compareTo(Gene o) {
        if (this.location.equals(o.location)) {
            return 0;
        } else {
            String[] split1 = splitLocation(this.location);
            String[] split2 = splitLocation(o.location);

            if (Integer.parseInt(split1[0]) > Integer.parseInt(split2[0])) {
                return 1;
            } else if (Integer.parseInt(split2[0]) > Integer.parseInt(split1[0])) {
                return -1;
            } else {
                if (split1[1].equals("p") && split2[1].equals("q")) {
                    return -1;
                } else if (split2[1].equals("p") && split1[1].equals("q")) {
                    return 1;
                } else {
                    if (Double.parseDouble(split1[2]) > Double.parseDouble(split2[2])) {
                        return 1;
                    } else if (Double.parseDouble(split2[2]) > Double.parseDouble(split1[2])) {
                        return -1;
                    } else {
                        return split1[3].compareTo(split2[3]);
                    }
                }
            }
        }
    }

    private String[] splitLocation(String location) {
        /*
        [0] chromosome
        [1] arm (p/q)
        [2] location
        [3] rest
         */

        String[] curLocation = new String[4];
        int splitQ = location.indexOf('q');
        int splitP = location.indexOf('p');
        int splitLoc1;
        int splitLoc2 = -1;

        if (splitQ == -1 && splitP == -1) {
            splitLoc1 = 1;
        } else if (splitQ == -1 || splitP != -1 && splitP < splitQ) {
            splitLoc1 = splitP;
        } else {
            splitLoc1 = splitQ;
        }

        for (int i = splitLoc1 + 1; i < location.length(); i++) {
            char checkChar = location.charAt(i);
            if ("0123456789.".indexOf(checkChar) == -1) {
                // Char found, split!
                splitLoc2 = i;
                break;
            }
        }
        curLocation[0] = location.substring(0, splitLoc1);
        if (location.length() == splitLoc1) {
            curLocation[1] = "";
            curLocation[2] = "";
            curLocation[3] = "";
        } else {
            curLocation[1] = location.substring(splitLoc1, splitLoc1 + 1);
            if (splitLoc2 == -1) {
                curLocation[2] = location.substring(splitLoc1 + 1);
                curLocation[3] = "";
            } else {
                curLocation[2] = location.substring(splitLoc1 + 1, splitLoc2);
                curLocation[3] = location.substring(splitLoc2);
            }
        }
        return fixChrom(curLocation);
    }

    private String[] fixChrom(String[] split) {
        try {
            Integer.parseInt(split[0]);
        } catch (NumberFormatException e) {
            switch (split[0].toUpperCase()) {
                case "-":
                    split[0] = "-3";
                    break;
                case "X":
                    split[0] = "-2";
                    break;
                case "Y":
                    split[0] = "-1";
                    break;
                default:
                    // Any other weird chromosome notation is just placed at the back
                    split[0] = "-4";
            }
        }
        if (split[2].isEmpty()) {
            split[2] = "0.0";
        }
        return split;
    }
}
