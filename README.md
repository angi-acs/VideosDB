# VideosDB
##### Corina-Angi Bălănescu
##### 321CD

### Actions
##### Action
- clasă abstractă moștenită de Command, Query și Recommendation în care este
definită metoda abstractă care va fi apelată în main pentru execuția acțiunilor
##### Command, Query & Recommendation
- câmpurile fiecărei clase reprezintă parametrii necesari execuției tipului de 
acțiune (nu sunt comuni => nu au reprezentare în Action)
- fiecare metodă execută câte un tip de comandă/query/recomandare (denumirile 
acestora fiind corespunzătoare tipului)

### Actor
##### Actor
- clasă ale cărei câmpuri reprezintă datele specifice unui obiect de tip Actor

### Common
##### Constants
- clasă în care am definit constantele de care am avut nevoie pentru citirea
datelor corespunzătoare acțiunilor

### Entertainment
##### Video
- clasă abstractă moștenită de Movie și Show în care sunt definite atât metode 
abstracte (care sunt comune celor două subclase ca și scop, dar nu ca și implementare), 
cât și o metodă care nu necesită diferențierea între cele două entități
##### Movie, Show & Season
- câmpurile fiecărei clase reprezintă datele specifice unui obiect de tip 
Movie/Show/Season

### Repository
##### Repository
- această clasă reprezintă baza de date
- câmpurile sunt exclusiv liste care conțin entitățile temei
- metodele regăsite aici sunt de trei tipuri:
    1. de căutare și găsire a unei entități având ca parametru numele acesteia
    2. de adăugat modificări asupra bazei de date (obținute în cadrul acțiunilor)
    3. de colectare a diferitelor informații care au legătură cu mai multe entități 
  simultan

### User
##### User
- clasă ale cărei câmpuri reprezintă datele specifice unui obiect de tip User

### Utils
##### Filter
- interfață cu o singură metodă care filtrează o listă în funcție de criteriile
primite ca parametru
- este implementată în Recommendation
##### Helpers
- clasă în care apelez constructorii din clasele Actor, User, Movie, Show, Command, 
Query și Recommendation pentru a le atribui câmpurilor valorile citite din input
##### Sort
- interfață cu o singură metodă care sortează un map în funcție de parametrii primiți
- este implementată în clasele Query și Recommendation

 
