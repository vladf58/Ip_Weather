# mtaWeather

Aplicatie GUI scrisă folosind JavaFX care afișează vremea în diferite locații.
## Funcționalități
Aplicația are următoarele funcționalități
- Posibilitatea determinării locației în funcție de adresa IP;
- Afișarea steagurilor pentru țările cunoscute;
- Posibilitatea adăugării de locații noi prin introducerea latitudinii și longitudinii;
- Afișarea vremii la momentul curent pentru o locație dorită;
- Afișarea prognozei meteo pentru următoarele 5 zile.

## Utilizare
Aplicația este intuitiv de folosit.
- Aplicația va încerca să stabilească locația pe baza adresei IP cu ajutorul unui API.
- Din meniul din stânga se alege țara dorită dintr-o listă încărcată pe baza unui fișier csv;
- După selectarea țării vor fi încărcate orașele din fișierul csv ce se află în țara selectată;
- După selectarea orașului, aplicația va afișa prognoza meteo din locația selectată.

Aplicația va încerca să afișeze și steagul țării selectate prin intermediul unui API.

## Referințe
 * http://www.geognos.com -API pentru determinarea steagului pentru fiecare țară.
*  https://ipapi.co -API pentru determinarea locației
* https://openweathermap.org -API folosit pentru determinarea datelor meteorlogice
* https://creativecommons.org/licenses/by/4.0/ -Furnizor pentru baza de date cu orașe. Aceasta a fost modificată pentru a facilita accesul la date al aplicației.
