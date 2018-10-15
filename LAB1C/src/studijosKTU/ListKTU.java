/**
 * @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 23
 *
 * Tai pirmoji kompleksinės duomenų struktūros klasė, kuri leidžia apjungti
 * atskirus objektus į vieną visumą - sąrašą. Objektai, kurie bus dedami į
 * sąrašą, turi tenkinti interfeisą Comparable<E>.
 *
 * Užduotis: Peržiūrėkite ir išsiaiškinkite pateiktus metodus. Metodų algoritmai
 * yra aptarti paslaitos metu. Realizuokite nurodytus metodus.
 *****************************************************************************
 */
package studijosKTU;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ListKTU<E extends Comparable<E>>
		implements ListADT<E>, Iterable<E>, Cloneable {

	private Node<E> first;   // rodyklė į pirmą mazgą
	private Node<E> last;    // rodyklė į paskutinį mazgą
	private Node<E> current; // rodyklė į einamąjį mazgą, naudojama getNext
	private int size;        // sąrašo dydis, tuo pačiu elementų skaičius

	/**
	 * Constructs an empty list.
	 */
	public ListKTU() {
	}

        
        public boolean containsAll(ListKTU<?> c)
        {
            for (Object e : c) {
                if (!contains(e)) {
                    return false;
                }
            }
            return true;
        }
        
        public boolean contains(Object o)
        {
            Iterator<E> it = iterator();
            if (o == null) {
                while (it.hasNext()) {
                    if (it.next() == null) {
                        return true;
                    }
                }
            } else {
                while (it.hasNext()) {
                    if (o.equals(it.next())) {
                        return true;
                    }
                }
            }
            return false;
        }
        
        
                
        public void addFirst(E e)
        {            
            Node<E> toAdd = new Node<E>(e, first);
            
            if (size != 0)
            {
                toAdd.next = first;
                first =  toAdd;
                size++;
            } else {
                first = toAdd;
                toAdd.next = null;
                size++;
            }       
        }
   
        public int lastIndexOf(Object o)
        {
            int index = 0;
            Node<E> el = first;
            for (int i = 0; i < size; i++)                         
            {
                if (el.element.equals(o))
                {
                    index = i;
                }
                el = el.next;
            }
            return index;
        }
  
	/**
	 * metodas add įdeda elementą į sąrašo pabaigą
	 * @param e - tai įdedamas elementas (jis negali būti null)
	 * @return true, jei operacija atlikta sėkmingai
	 */
	@Override
	public boolean add(E e) {
		if (e == null) {
			return false;   // nuliniai objektai nepriimami
		}
		if (first == null) {
			first = new Node<>(e, first);
			last = first;
		} else {
			Node<E> e1 = new Node(e, null);
			last.next = e1;
			last = e1;
		}
		size++;
		return true;
	}

	/**
	 * Įterpia elementą prieš k-ąją poziciją
	 *
	 * @param k pozicija
	 * @param e įterpiamasis elementas
	 * @return jei k yra blogas, grąžina null
	 */
	@Override
	public boolean add(int k, E e) {
            
            Node temp = new Node(e);
            Node curr = first;
            
            if (e == null) {
                    return false;
            }
            if (k < 0 || k >= size) {
                    return false;
            }		
            if (k == 0) {
                temp.setNext(first);
                this.first = temp;
            }
            else {
                for (int i = 1; i < k; i++) {
                    curr = curr.getNext();
                }
                temp.setNext(curr.getNext());
                curr.setNext(temp);
            }
            this.size++;
            return true;         
	}
        
        
        public Node getNode(int index)
        {
            if (index > size) {
                throw new IllegalArgumentException("The index [" + index +
                        "] is greater than the current size");
            }
            Node current = first;
            for (int i = 1; i < index; i++) {
                current = current.getNext();
            }
            return current;
        }
        


	/**
	 *
	 * @return sąrašo dydis (elementų kiekis)
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * patikrina ar sąrašas yra tuščias
	 *
	 * @return true, jei tuščias
	 */
	@Override
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Išvalo sąrašą
	 */
	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
		current = null;
	}

	/**
	 * Grąžina elementą pagal jo indeksą
	 *		(pradinis indeksas 0)
	 * @param k indeksas
	 * @return k-ojo elemento reikšmę; jei k yra blogas, gąžina null
	 */
	@Override
	public E get(int k) {
		if (k < 0 || k >= size) {
			return null;
		}
		current = first.findNode(k);
		return current.element;
	}

	/**
	 * Pakeičia k-ojo elemento reikšmę
	 *
	 * @param k keičiamo elemento indeksas
	 * @param e nauja elemento reikšmė
	 * @return senoji reikšmė
	 */
	@Override
	public E set(int k, E e) {
            
            Node tempor = getNode(k);
            if (k > size) {
                throw new IllegalArgumentException("The index [" + k +
                        "] is greater than the current size");
            } else {
                Node temp = new Node(e);
                Node current = getNode(k);
                
                if (k == 0) {
                    temp.setNext(first);
                    first = temp;
                    last = first;
                } else {
                    temp.setNext(current.getNext());
                    current.setNext(temp);
                }                
                if (k == size - 1) {
                    last.setNext(temp);
                    last = temp;
                }             
            }
            size++;
            return (E) tempor.element;
	}

	/**
	 * pereina prie kitos reikšmės ir ją grąžina
	 *
	 * @return kita reikšmė
	 */
	@Override
	public E getNext() {
		if (current == null) {
			return null;
		}
		current = current.next;
		if (current == null) {
			return null;
		}
		return current.element;
	}

	/**
	 * Šalina elementą pagal indeksą
	 *
	 * @param k šalinamojo indeksas
	 * @return pašalintas elementas
	 */
	@Override
	public E remove(int k) {
            Node tempor = getNode(k);
            if (k == 0) {
                first = first.getNext();
            } else {
                getNode(k).setNext(getNode(k).getNext().getNext());
            }
            this.size--;
            return (E) tempor.element;
	}

	/**
	 *
	 * @return sąrašo kopiją
	 */
	@Override
	public ListKTU<E> clone() {
		ListKTU<E> cl = null;
		try {
			cl = (ListKTU<E>) super.clone();
		} catch (CloneNotSupportedException e) {
			Ks.ern("Blogai veikia ListKTU klasės protėvio metodas clone()");
			System.exit(1);
		}
		if (first == null) {
			return cl;
		}
		cl.first = new Node<>(this.first.element, null);
		Node<E> e2 = cl.first;
		for (Node<E> e1 = first.next; e1 != null; e1 = e1.next) {
			e2.next = new Node<>(e2.element, null);
			e2 = e2.next;
			e2.element = e1.element;
		}
		cl.last = e2.next;
		cl.size = this.size;
		return cl;
	}
    //  !

	/**
	 * Formuojamas Object masyvas (E tipo masyvo negalima sukurti) ir surašomi
	 * sąrašo elementai
	 *
	 * @return sąrašo elementų masyvas
	 */
	public Object[] toArray() {
		Object[] a = new Object[size];
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			a[i++] = e1.element;
		}
		return a;
	}

	/**
	 * Masyvo rikiavimas Arrays klasės metodu sort
	 */
	public void sortSystem() {
		Object[] a = this.toArray();
		Arrays.sort(a);
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			e1.element = (E) a[i++];
		}
	}

	/**
	 * Rikiavimas Arrays klasės metodu sort pagal komparatorių
	 *
	 * @param c komparatorius
	 */
	public void sortSystem(Comparator<? super E> c) {
		Object[] a = this.toArray();
		Arrays.sort(a, (Comparator) c);
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			e1.element = (E) a[i++];
		}
	}

	/**
	 * Sąrašo rikiavimas burbuliuko metodu
	 */
	public void sortBuble() {
		if (first == null) {
			return;
		}
		for (;;) {
			boolean jauGerai = true;
			Node<E> e1 = first;
			for (Node<E> e2 = first.next; e2 != null; e2 = e2.next) {
				if (e1.element.compareTo(e2.element) > 0) {
					E t = e1.element;
					e1.element = e2.element;
					e2.element = t;
					jauGerai = false;
				}
				e1 = e2;
			}
			if (jauGerai) {
				return;
			}
		}
	}

	/**
	 * Sąrašo rikiavimas burbuliuko metodu pagal komparatorių
	 *
	 * @param c komparatorius
	 */
	public void sortBuble(Comparator<? super E> c) {
		if (first == null) {
			return;
		}
		for (;;) {
			boolean jauGerai = true;
			Node<E> e1 = first;
			for (Node<E> e2 = first.next; e2 != null; e2 = e2.next) {
				if (c.compare(e1.element, e2.element) > 0) {
					E t = e1.element;
					e1.element = e2.element;
					e2.element = t;
					jauGerai = false;
				}
				e1 = e2;
			}
			if (jauGerai) {
				return;
			}
		}
	}

	/**
	 * Sukuria iteratoriaus objektą sąrašo elementų peržiūrai
	 *
	 * @return iteratoriaus objektą
	 */
	@Override
	public Iterator<E> iterator() {
		return new ListIteratorKTU();
	}

	/**
	 * Iteratoriaus metodų klasė
	 */
	class ListIteratorKTU implements Iterator<E> {

		private Node<E> iterPosition;

		ListIteratorKTU() {
			iterPosition = first;
		}

		@Override
		public boolean hasNext() {
			return iterPosition != null;
		}

		@Override
		public E next() {
			E d = iterPosition.element;
			iterPosition = iterPosition.next;
			return d;
		}
                
		@Override
		public void remove() {
			while(iterator().hasNext())
                        {
                            Object o = iterator().next();
                            if (o.equals(o)) {
                                iterator().remove();
                            }
                        }
		}
                
                
        }

	/**
	 * Vidinė mazgo klasė
	 *
	 * @param <E> mazgo duomenų tipas
	 */
	private static class Node<E> {

		private E element;    // ji nematoma už klasės ListKTU ribų
		private Node<E> next; // next - kaip įprasta - nuoroda į kitą mazgą

		Node(E data, Node<E> next) { //mazgo konstruktorius
			this.element = data;
			this.next = next;
		}
                public Node(E data) {
                    this(data, null);
                }

		/**
		 * Suranda sąraše k-ąjį mazgą
		 *
		 * @param k ieškomo mazgo indeksas (prasideda nuo 0)
		 * @return surastas mazgas
		 */
		public Node<E> findNode(int k) {
			Node<E> e = this;
			for (int i = 0; i < k; i++) {
				e = e.next;
			}
			return e;
		}             
                
                public Node getNext() {
                    return this.next;
                }
                
                public void setNext(Node nextNode) {
                    this.next = nextNode;
                }
	} // klasės Node pabaiga
}
