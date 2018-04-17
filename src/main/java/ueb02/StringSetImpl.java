package ueb02;
import java.lang.String;
import java.util.NoSuchElementException;

public class StringSetImpl implements StringSet {

    public class Element {
        private String value;
        private Element left, right;

        public Element(String v, Element le, Element re) {
            value = v;
            left = le;
            right = re;
        }

        public int size(Element e) {
            int s = 1;

            if (e.left != null) {
                s += e.left.size(e.left);
            }
            if (e.right != null) {
                s += e.right.size(e.right);
            }
            return s;
        }

        public String toString() {
            String s = " " + value;
            if (left != null) {
                s += ", " + left.toString();
            }
            if (right != null) {
                s += ", " + right.toString();
            }
            return s;
        }
    }

    private Element root;// das ist die Wurzel, bzw das erste Element

    @Override
    public boolean add(String s) {
        Element e = new Element(s, null, null);// dann erstellen wir ein neues Element
        if (root == null) {// wenn root leer ist
            root = e;// setze das neue Element als root
            return true;
        }

        Element it = root; // in dem fall ist root bereits belegt
        while (it.value != null) {// solange nächstes Element vorhanden ist
            if (it.value.equals(e.value)) {// wenn ein neues Elemnt bereits enthalzen ist
                return false; // mach das
            } else if (s.compareTo(it.value) < 0) {/* zweite Vergleich ob nach links oder nach rechts kommen soll. Hier vergleichen wir
                den String mit neuem Element*/
                if (it.left == null) {// und hier schauen wir ob in den linken Seite steht was drinnen
                    it.left = e; // weil das leer ist, tu das in die linke Seite
                    return true;
                } else {
                    it = it.left;// // wenn das nicht leer ist, dann gib mir den Element
                }
            } else {
                if (it.right == null) {
                    it.right = e;
                    return true;
                } else {
                    it = it.right;
                }
            }
        }
        return false;
    }



    @Override
    public boolean contains(String s){// die Methode prüft ob das Element enthalten ist
        if(root==null){
        return false;
        }
        Element it = root;// hier ist ein neues Element die Wurzel
        while(it != null){// solange dieses Element nicht leer ist
            System.out.println("--> " + it.value + " -- " + s );
            if(s.equals(it.value)){// wenn dieses Element enthalten ist
                return true;// gib das aus
            }else if(s.compareTo(it.value)<0) {// und wenn nicht
                it = it.left;
			} else {
                it = it.right;
                }
        }
        return false;
    }

    @Override
    public String remove(String s) {
        if (root == null) {// wenn dieses Element leer ist
            throw new NoSuchElementException();
        }

        if (root.value == s) {// wenn root ist das Element
            return removeRoot();// dann rufe diese Methode auf
        }

        Element it = root;// mit dem Knoten beginnen
        while (it != null) {// solange das Elemnt nicht leer ist
            if (s.compareTo(it.value) < 0) {
                if (it.left == null && it.left.value == s) {
                    it = it.left;
                    return removeElement(it, it.left);
                } else {
                    if (it.right == null && it.right.value == s) {
                        it = it.right;
                        return removeElement(it, it.right);
                    }
                }

            }
        }
        return s;
    }

    private String removeRoot(){
        Element e = root;
        if(e.right==null && e.left== null){
            root=null;
        }else if(e.left==null){
            root=e.right;
        }else if(e.right==null){
            root=e.left;
        } else{
            root=e.left;
            addElement(e.right);
        }
        return e.value;
    }

    private String removeElement(Element par, Element e){
        if(e==par.left){
            par.left=null;
        }else {
            par.right=null;
        }
        addElement(e.left);
        addElement(e.right);

        return e.value;
    }

    private void addElement(Element e){
        if(e==null){
            return;
        }

        if(root==null){
            root=e;
            return;
        }

        Element it = root;
        while(it!=null){
            if(it.value==e.value){
                return;
            }else if(e.value.compareTo(it.value)<0){
                if(it.left==null){
                    it.left=e;
                    return ;
                }else {
                    it=it.left;
                }
            }else{
                if(it.right==null){
                    it.right=e;
                    return;
                }else {
                    it=it.right;
                }
            }
        }
        return;
    }

    @Override
    public int size() {
        if(root==null) {
            return 0;
        }else {
            return root.size(root);
        }
    }

    @Override
    public String toString() {
        if (root == null) {
            return "[]";
        }else{
            return "[" + root.toString() + "]";
            }
        }
    }
