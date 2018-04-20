public class RecursiveMaze{

    public String tekst;

    public int width;
    public int height;

    public int indexX = 10;
    public int indexY = 10;

    public String wall[][]; //Arrayet der danner grundlag for den grafiske repræsentation i konsollen

    public boolean visited[][]; //Arrayet der benyttes til at tjekke om en celle er besøgt

    public int randomdir;

    public boolean north;
    public boolean east;
    public boolean south;
    public boolean west;

    public int tal = 1000; /*Sikkerheds tal - vi støder af og til ind i infinite loops og stackoverflow grundet fejl.
                            Vi har ikke nået at finde fejlen, så vi benytter et tal der sikre et vist antal iterationer*/

    RecursiveMaze(int width, int height){
        this.width = width;
        this.height = height;
    }

    //Setup af et fyldt array med @ chars. Vi erstatter disse med ' ' for at danne vejen igennem labyrinten.
    public void initLabyrinth() {

        wall = new String[width][height];
        for(int i = 0; i < height;i++){
            for(int j = 0; j < width;j++ ){
                wall[j][i] = "@";
            }
        }

        visited = new boolean[width][height];

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                visited[j][i] = false;
            }
        }
    }
    public void randomGen(int randomdir){

        north = false;
        east = false;
        south = false;
        west = false;

        if (randomdir < 25){
            north = true;
        } else if (randomdir > 25 && randomdir < 50) {
            east = true;
        } else if (randomdir > 50 && randomdir < 75){
            south = true;
        } else if (randomdir > 75 && randomdir < 100){
            west = true;
        }
    }
    /* Recursive metode til dannelse af vej igennem maze. Den kører makeWay() igen hvis if statement er true og bliver ved indtil ingen returner true.
    * Grundet stackoverflow fejl vi ikke kunne nå at fixe har vi implementeret et sikkerhedstal (int tal) der garanterer et max antal iterationer.
    * Hele metoden vælger tilfældigt mellem nord, syd, øst eller vest via int randomdir og randomGen metoden. Derefter tjekkes om
    * den celle metode bevæger sig imod er tom - hvis den er tom, så ændres char til ' ' og den markeres som besøgt.
    * Hvis ikke, så kører den sidste if løkke igennem, som gentager metoden igen, rekursivt.
    * Ideelt set vender den tilbage en position i arrayet, men vi har ikke kunne få dette til at fungere uden enten stackoverflow error eller arrayoutofbounds.
    * Dette er umiddelbart første ting der skal fixes efter aflevering.
    * */
    public void makeWay(){

        randomdir = 0;

        randomdir = (int)(Math.random()*100);
        randomGen(randomdir);

        if (indexY-1 > 0 && indexY-1 < height-1 && north && !visited[indexX][indexY-1]) {
            wall[indexX][indexY] = " ";
            visited[indexX][indexY] = true;
            wall[indexX][indexY-1] = " ";
            visited[indexX][indexY-1] = true;
            indexY -=2;
            makeWay();

        }
        else if (indexY-1 > 0 && indexY-1 < height-1 && north && visited[indexX][indexY-1]) {
            indexY -=2;
            makeWay();
        }
        else if (indexX+1 >0 && indexX+1 < width-1 && east && !visited[indexX + 1][indexY] ) {
            wall[indexX][indexY] = " ";
            visited[indexX][indexY] = true;
            wall[indexX + 1][indexY] = " ";
            visited[indexX + 1][indexY] = true;
            indexX +=2;
            makeWay();

        }
        else if (indexX+1 >0 && indexX+1 < width-1 && east && visited[indexX + 1][indexY] ) {
            indexX +=2;
            makeWay();

        }
        else if (indexY+1 > 0 && indexY+1 < height-1 && south && !visited[indexX][indexY + 1]) {
            wall[indexX][indexY] = " ";
            visited[indexX][indexY] = true;
            wall[indexX][indexY + 1] = " ";
            visited[indexX][indexY + 1] = true;
            indexY += 2;
            makeWay();
        }
        else if (indexY+1 > 0 && indexY+1 < height-1 && south && visited[indexX][indexY + 1]) {
            indexY +=2;
            makeWay();

        } else if (indexX-1 > 0 && indexX-1 < width-1 && west && !visited[indexX - 1][indexY]) {
            wall[indexX][indexY] = " ";
            visited[indexX][indexY] = true;
            wall[indexX - 1][indexY] = " ";
            visited[indexX - 1][indexY] = true;
            indexX -=2;
            makeWay();
        }

        else if (indexX-1 > 0 && indexX-1 < width-1 && west && visited[indexX - 1][indexY]) {
            indexX -=2;
            makeWay();
        }

        if(indexX > 0 && indexX < width - 1 && indexY > 0 && indexY < height-1 && tal>0){
/*
                    if ((indexX-1 > 0 && indexX-1 < width-1 && west && visited[indexX - 1][indexY])){
                        indexX+=1;
                    }
                    if ((indexX+1 > 0 && indexX+1 < width-1 && east && visited[indexX + 1][indexY])){
                        indexX-=1;
                    }
                    if ((indexY+1 > 0 && indexY+1 < width-1 && south && visited[indexX][indexY + 1])){
                        indexY-=1;
                    }
                    if ((indexY-1 > 0 && indexY-1 < width-1 && north && visited[indexX][indexY - 1])){
                        indexY+=1;
                    }*/

            if (!(visited[indexX+1][indexY]) || !(visited[indexX-1][indexY]) || !(visited[indexX][indexY+1]) || !(visited[indexX][indexY-1])){
                tal-=1;
                makeWay();
            }

        }
    }

    public void lavvej() {

        randomdir = 0;

        if (indexX > 0 && indexX < width - 1 && indexY > 0 && indexY < height - 1 && tal>0 ) {

            if (!(visited[indexX + 1][indexY]) || !(visited[indexX - 1][indexY]) || !(visited[indexX][indexY + 1]) || !(visited[indexX][indexY - 1])) {
                randomdir = (int) (Math.random() * 100);
                randomGen(randomdir);

                if (north) {

                    wall[indexX][indexY] = " ";
                    visited[indexX][indexY] = true;
                    wall[indexX][indexY - 1] = " ";
                    visited[indexX][indexY - 1] = true;

                    indexY -= 1;

                } else if (south) {
                    wall[indexX][indexY] = " ";
                    visited[indexX][indexY] = true;
                    wall[indexX][indexY + 1] = " ";
                    visited[indexX][indexY + 1] = true;

                    indexY += 1;

                } else if (west) {
                    wall[indexX][indexY] = " ";
                    visited[indexX][indexY] = true;
                    wall[indexX - 1][indexY] = " ";
                    visited[indexX - 1][indexY] = true;

                    indexX -= 1;

                } else if (east) {
                    wall[indexX][indexY] = " ";
                    visited[indexX][indexY] = true;
                    wall[indexX + 1][indexY] = " ";
                    visited[indexX + 1][indexY] = true;

                    indexX += 1;
                }

                lavvej();
            }
               /*  else if ((visited[indexX + 1][indexY]) && (visited[indexX - 1][indexY]) && (visited[indexX][indexY + 1]) && (visited[indexX][indexY - 1])) {

                    if (north) {
                        indexY -= 2;
                    } else if (south) {
                        indexY += 2;
                    } else if (west) {
                        indexX -= 2;
                    } else if (east) {
                        indexX += 2;
                    }
                    lavvej();

                }*/
        }


    }

    public void printlab() {
        for (int i = 0; i < height; i++){
            tekst = " ";
            for (int j = 0; j < width; j++){
                tekst = tekst.concat(wall[j][i]);
            }
            System.out.println(tekst);
        }
    }
}