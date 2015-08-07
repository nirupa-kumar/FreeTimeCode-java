class Anagram {
  public static void main(String[] args) {
    String [] stringIn = {"art","rat","tar", "cat", "act", "horse", "mouse"};
    String[] stringout = new String[7];
    HashMap<String,ArrayList<String>> h = new HashMap<>();
    for(int i = 0 ; i<stringIn.length;i++){
      char[] charArray = stringIn[i].toCharArray();
      Arrays.sort(charArray);
      stringout[i] = new String(charArray);
      ArrayList<String> templist = h.get(stringout[i]);
      if(templist == null){
        ArrayList<String> list = new ArrayList<String>();
        list.add(stringIn[i]);
        h.put(stringout[i],list);
      }else{
        templist.add(stringIn[i]);
      }
    }
    Set<String> keys = h.keySet();
    for(String s: keys){
      System.out.println(h.get(s));
    }


  }
}
