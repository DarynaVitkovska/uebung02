package ueb02;

class Duplikate {

	/**
	 * Gibt ein StringSet mit den Wörtern zurück, welche mindestens zwei mal im Text vorkommen.
	 * Alle Satzzeichen im Text sollen ignoriert werden.
	 * @param text Eingabetext, kann Satzzeichen enthalten welche ignoriert werden.
	 * @return StringSet mit den Wörtern, welche mind. zwei mal vorkommen.
	 */
	static StringSet findeDuplikate (String text) {
		StringSetImpl set = new StringSetImpl();
		StringSetImpl setReturn = new StringSetImpl();

		// TODO Implementieren Sie die Methode gemäß dem obigen Javadoc Kommentar.
		String[] x = text.split(" ");// wenn ein leerzeichen kommt, teile den String auf

		for(int i= 0; i<x.length;i++){
			System.out.println(" --- " + x[i]);
			x[i] = x[i].replaceAll("[^a-z, A-Z]", "");
			if(!set.contains(x[i])){// wenn in dem set dieses  E nicht gibt
				set.add(x[i]);// dann füge es dem set hinzu
				// System.out.println("set --> " + x[i]);
			} else {// wenn dieses E schon im set gibt, dann füge dies in den setReturn hinzu
				if(!setReturn.contains(x[i])){// wenn deises E im setReturn schon enthalten ist
					setReturn.add(x[i]); //
					// System.out.println("setReturn --> " + x[i]);
				}
			}
			System.out.println(set);
			System.out.println(setReturn);

		}
		return setReturn;
	}
}
