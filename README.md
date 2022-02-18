Járműkezelő szoftver - 
Dániel Ádám Zoltán - 
SZÁMALK-Szalézi Szakgimnázium - 
Szoftverfejlesztő


1.	Bevezetés
A szakdolgozatom témája egy járműkereskedés járműveinek számon tartása és kezelé-se, mindemellett egy gumiabroncs és az autókkal kapcsolatos kisebb szervizelések feladatának ellátása. Fontos volt számomra, hogy olyan témát válasszak, mellyel segí-teni tudom más munkáját, megtanuljam a program által használt programozási nyelve-ket és elsajátítsam a szoftverfejlesztést, mely izgalmas, mivel folyton új dolgokat tanu-lok.
Szakdolgozatom célja tehát egy olyan program megalkotása melyet megrendelésre készítettem, hogy megkönnyítsem a megrendelő munkáját és azáltal, hogy ezt a prog-ramot használva kevesebb idő alatt több ügyfelet tudjon kezelni és nagyobb bevételre tudjon szert tenni.

Azért ezt a platformot választottam, mivel számomra mindig is a számítógépes plat-form marad a kedvencem. Ezt ismerem a legjobban, és a későbbiekben is ezzel szeret-nék foglalkozni. A fejlesztési nyelv nem volt kérdés, mikor elkezdtem a tanulmányai-mat az iskolában a JAVA nyelvvel ismerkedtem meg először, és nagyon megtetszett. Az adatkezelés egy másik kedvenc témám, véleményem szerint az adatok ma is és mindig is fontos alapjai lesznek az informatikának, ezért érdemes ezzel a témakörrel foglalkozni.

Az elkészítés során Microsoft Windows 10 Profession magyar nyelvű operációs rend-szert, NetBeans IDE 8.2 fejlesztői környezetet, JAVA 1.8.0_161 verziószámú JDK-t, MySQL 5.0.12 adatbáziskezelő szoftvert, XAMPP 3.2.4 webszervert használtam. A program kinézetét a Java beépített Swinges GUI widget eszközkészletével szeretném elkészíteni, tehát Desktop alkalmazás, amit számítógépen lehet futtatni. 
2.	Feladatspecifikáció
Az általam írt program elkészítésével két célom volt, az egyik cél egy járműkereskedés járművek adatainak megtekintése, felvétele, törlése és módosítása. A programom má-sik része az autószereléshez kötődik, ha egy adott ügyfél behozza a járművét azon a gumiabroncsaival kapcsolatos szervizelések, és az autóhoz köthető szervizelések, javí-tások, alkatrész cserék, a jármű műszaki vizsgára való felkészítése, és vizsgáztatását lehet ellátni. Mivel a járműkereskedés és a szerviz egy telephelyen van, így azonos nyitvatartási idő érvényes mind a kettőre. Minden hétköznap reggel nyolc-tol, este öt óráig van nyitva a telephely, ezenkívül szombaton ként reggel nyolc-tol délután kettő-ig. A programot úgy terveztem, hogy több felhasználó is használni tudja, de csak egy felhasználó fogja használni. Az alkalmazás asztali rendszerként kerüljön fejlesztésre.
A funkciókkal szembeni általános elvárások
	Új adat felvitele esetén biztosítani kell, hogy csak olyan adat kerüljön mentésre, ahol a kötelező adatok ki vannak töltve, és az adat megfelel az elvárt adat típusának.
Állapot változás esetén biztosítani, kell, hogy a változtatott adat korábbi állapo-ta megmaradjon. Az új állapot, pedig megfeleljen az adott adat típusának. Az újadat típusának meg kell felelnie az elvárt adat típusának.
Adat javítás estén, biztosítani, hogy csak a hatályos adaton lehessen javítani, új állapot létrehozása nélkül.
Adat törlése esetén, biztosítani kell, hogy csak az adott adatott lehessen kitörlni.

2.1	Járműkereskedés specifikálása
A járműkereskedéshez tartozó részfunkciók a következőek, a telephelyen lévő jármű-veket az alkalmazással meg lehet tekinteni, módosítani, hozzáadni, és javítani a meg-adott jármű adatait, ezenkívül a járművekhez tartozó képeket lehet megtekinteni és hozzáadni, vagy eltávolítani. A járműkereskedés rendelkezik egy központi telephellyel, ahol a járműveket tároljuk, ha egy adott ügyfél vásárolni szeretne, akkor megtekintheti őket. Az ügyfelek csak a nyitvatartási időn belül tekinthetik meg a járműveket. Mivel három fajta járművet szeretnék tárolni a telephelyen, ezek el vannak egymástól szepa-rálva az átláthatóság miatt.
•	személyautó
•	motorkerékpár/quad
•	haszonjármű
Mind a három fajta járműnek vannak tulajdonságaik melyek hasonlítanak egymásra, bizonyos szempontból. A közös tulajdonságok a következőek:
 
•	rendszám	
•	márka
•	modell
•	típus
•	évjárat
•	hónap
•	állapot
•	üzemanyag
•	hengerűrtarta-lom
•	kilométeróra ál-lás
•	teljesítmény
•	szín
•	sebességváltó fajtája
•	jármű leírása
•	jármű hibái
•	vételár
•	engedmény
 
Minden egyes járművet a saját rendszáma határozz meg. A rendszám hét karakterből áll: három nagybetűből, melyek az angol ábécé betűiből állnak. A betűk után egy kötő-jel van, amit három darab szám követ. A járművek maximális súlya 9999 kilogramm lehet, e fölötti súlyú járművel a kereskedés nem foglalkozik. Egyes járműtípusoknál vannak egyedi tulajdonságok, vagy esetleg hiányzik valamelyik tulajdonság amelyik a többi járműnél létezik. Mind a három járműtípusnál adott a jármű súlya. A járművek kivitele több típusú is lehet, ezek járműveként változóak. Személyautóknál a követke-zőek lehetnek: cabrio, coupe, egyterű, ferdehátú, kisbusz, kombi, lépcsőshátú, pickup, sedan, sport, terepjáró, városi terepjáró, egyéb. A motorkerékpároknál a következőek lehetnek: chopper, classic(veterán), cross, enduro, gyerekmotor, gyorsasági(sport), ol-dalkocsis, quad, robogó, segédmotoros kerékpár, supermoto, trial, túra, versenymotor, egyéb. A haszonjárműveknél a következőek lehetnek: billencs, darugémes, darus, do-bozos, duplakabinos, harci jármű, hűtős, járműszállító, konténerszállító, mentő, nyer-ges szerelvény, platós, ponyvás, tartályos, tűzoltó, egyéb. A járművek állapotai már azonosak, ezek a következőek: normál, kitűnő, megkímélt, újszerű, sérülésmentes, sérült, enyhén sérült, eleje sérült, hátulja sérült, baloldala sérült, jobboldala sérült, hiá-nyos, hibás. Több fajta üzemanyaggal működnek a járművek. A motorkerékpár csak háromfajta üzemanyaggal kapható, ezek a következőek: benzin, dízel, elektromos. A személyautónál, és a haszonjárműnél több fajta lehetőség adott: benzin, dízel, ben-zin/gáz, hibrid, hibrid(benzin), hibrid(dízel), elektromos, etanol, biodízel, gáz. A telje-sítmény kilowattban van értelmezve, de egy beépített metódussal ezt át szeretném vál-tani lóerőre és mellé íratni. A jármű színeire pontos jogszabályt nem találtam csak a lényeget, ami 01-es számkóddal indul és 10-ig megy, tehát a járművek színei összesen tíz féle lehet: fehér (01), sárga (02), narancs (03), piros (04), bíbor/lila (05), kék (06), zöld (07), szürke (08), barna (09), fekete (10). A sebességváltó értéke lehet: 0(mechanikus), 1(félautomata), 2(automata), vagy 3(szekvenciális). Mindegyik jármű-höz tartozik egy leírás és egy rövid vélemény az adott jármű aktuális és típus hibáiról. A tulajdonos által megvásárolt járműveket, melyek még nincsenek a telephelyen, ter-mészetesen kezelni szeretném a programomban. Ha megérkezett az adott jármű a te-lephelyre, akkor átkerül a telephelyre a többi jármű közé.

2.2	Személyautó szerviz specifikálása
A szervizhez tartozó részfunkciók a következőek, az ügyfél adatainak felvitele, az autó adatainak felvitele, munkalap létrehozása, az adott feladatokkal, és a szereléshez szük-séges alkatrészekkel. Adott ügyfél adott személyautót a nyitvatartási időn belül hozhat-ja és viheti el a szervizből. Mikor az ügyfél behozza az autóját, meg kell adja az ok-mányazonosító számát, nevét, és elérhetőségét. Természetesen az autó információi a legfontosabbak a szervizelés szempontjából, ezért az autóról a következő információ-kat kell eltárolni. Az autó rendszáma határozza meg az autót, de ezenkívül a márkáját, évjáratát, típusát, színét, motorszámát, és alvázszámát kell megadni. Minden egyes autó rendelkezik egy munkalappal, amin a szereléshez köthető információk találhatóak. A munkalapon a következő információk találhatók, a munkalap sorszáma, az adott autó rendszáma, a szerelésekhez tartozó kolléga neve, a hibajegyzék, ami meghatároz-za mit kell csinálni az adott járművel, a munkadíj, anyagdíj, vállalási határidő. Egy autóhoz egy szerelő van hozzárendelve, az átláthatóság érdekében. A munkalap ezen-kívül tartalmazza a feladatokat, amin az aktuális és már befejezett tennivalók találha-tóak. Mivel a legtöbb szereléshez új alkatrész szükséges, ezért fel van tűntetve a mun-kalapon. Az alkatrészeket, amik szükségesek az aktuális szereléshez a munkalap sor-száma határozza meg. Tehát a munkalap tartalmazza az elvégzett feladatokat a mun-kadíjjakkal, és az elnevezésekkel, és az éppen szükséges alkatrészek kódját, megneve-zését, és árát. Ezenkívül a megadott adatokból PDF-et szeretnék generálni munkalap néven, hogy igény szerint az ügyfél kapjon egy kézzelfogható dokumentumot.
3.	Tervezés
3.1	A program funkciói
•	felhasználó bejelentkeztetése, regisztrációja
•	személyautó keresése, felvitele, törlése, módosítása
•	motorkerékpár keresése, felvitele, törlése, módosítása
•	haszonjármű keresése, felvitele, törlése, módosítása
•	keresett jármű adatainak és képeinek megjelenítése
•	ügyfél felvitele, módosítása
•	munkalap létrehozása
•	feladat felvitele
•	szerelés felvitele
•	alkatrész és beszerzés felvitele
•	PDF generálása a megadott szerviz adatok alapján
3.2	Adatbázis szerkezet leírása
Az jármű kereskedés autóit külön táblákba tárolom az egyszerűbb lekérdezés érdeké-ben, mivel mikor keresünk egy járművet egyszere csak egy fajta járműre keresünk rá. Ezenkívül különböző márkájú autók szervizmunkáit tartom nyilván. Az autókat az ügyfelek hozzák be, akiknek nyilvántartjuk a nevét, elérhetőségeit. Amikor behoznak egy autót, nyitunk neki egy új munkalapot egyedi munkalapszámmal, amin feltüntet-jük, hogy mik a problémák az autóval. A választható feladat lehet valamely autórész javítása vagy cseréje, de akár vizsgára való felkészítés vagy műszaki vizsgáztatás is. Minden feladatnak óradíja és/vagy anyagköltsége van.  Amennyiben alkatrészcserét igényel a feladat, akkor a kívánt alkatrészt meg kell rendelni az (egyik) alkatrészbolt-tól. A munkalap feladatainak elvégzését az egyes szerelők a ráfordított munkaórák bejegyzésével zárják. Ebben a fázisban már jelezhetjük az ügyfélnek, hogy kész az autó, akinek elvitelkor ki kell fizetnie az összeget a szervizelésért. Az ügyfélt a szemé-lyi okmányszáma és típusa határozza meg. A szervizben lévő autók márkáit a márka nevű táblában tárolom. A bejelentkezéshez három táblát terveztem: a felhasználó, az engedélyek, és az felhasználókhoz tartozó engedély táblát.
 

3.2.1	Tábla szerkezetek

KEDVEZMENYSAV: bizonyos összegekhez tartozó kedvezmények törzse
KEDVEZMENYSAV: {kedvezmeny_azonosito, osszeg}

UGYFEL: az ügyfelek törzse
UGYFEL: {szemelyazon_okmany_szam, szemelyazon_okmany_tipus, nev, telszam, lakcim}

MARKA: tábla a márkák nyilvántartásához
MARKA: {mkod, marka}

DOLGOZO: a dolgozók törzse
DOLGOZO: {dkod, nev, munkakor}

AUTO: az autók törzse
AUTO: {rendszam, ugyfel_azon, marka, evjarat, tipus, szin, motorszam, alvazszam}

MUNKALAP: a behozott autók adatait tárolja, hogy milyen hibákat kell javítani az autón, a munkadíjat, az anyagdíjat, és a vállalási határidőt
MUNKALAP: {munkalap_szam, rendszam, hibajegyzek, munkadij, anyagdij, vallala-si_hatarido}

SZERELES: az autóhoz tartozó feladatokat tárolja a hozzárendelt szerelővel, a szerelés kezdésével és befejezésével, és a szereléssel eltöltött időt.
SZERELES: {munkalap_szam, szerelo, feladat, kezdes, befejezes, munkaora}

FELADAT: a konkrét feladat fajtákat tárolja a hozzátartozó általános munkadíjjal, és elnevezéssel, illetve a feladat típusait
FELADAT: {feladat, munkadij, elnevezes, tipus (’j’, ’c’ , ’f’ , ’v’)}

BESZERZES: a feladatokhoz tartozó anyagbeszerzés törzse
BESZERZES: {besz_kod, munkalap_szam,  alkatresz, aron, megrendelve}

ALKATRESZ: alkatrészek törzse
ALKATRESZ: {kod, megnevezes}

KERESKEDES_AUTO: személyautók törzse
KERESKEDES_AUTO: {rendszam, marka, modell, tipus, evjarat, honap, kivitel, allapot, uzemanyag, hengerur_tart, kilometerora_all, sajat_tomeg, teljesitmeny, szin, seb_valto, jarmu_leirasa, jarmu_hibai, telephely, vetelar, engedmeny}

KERESKEDES_MOTOR: motorkerékpárok törzse
KERESKEDES_MOTOR: {rendszam, marka, modell, tipus, evjarat, honap, kivitel, alla-pot, uzemanyag, hengerur_tart, kilometerora_all, sajat_tomeg, teljesitmeny, szin, seb_valto, jarmu_leirasa, jarmu_hibai, telephely, vetelar, engedmeny}

KERESKEDES_KAMION: haszonjárművek törzse
KERESKEDES_KAMION: {rendszam, marka, modell, tipus, evjarat, honap, kivitel, allapot, uzemanyag, hengerur_tart, kilometerora_all, sajat_tomeg, teljesitmeny, szin, seb_valto, jarmu_leirasa, jarmu_hibai, telephely, vetelar, engedmeny}

FELHASZNALO: felhasználók törzse
FELHASZNALO: {felhasznalo_kod, vezetek_nev, kereszt_nev, felhasznalo_nev, jelszo}

FELH_ENGED: adott felhasználóhoz tartozó engedély szintje
FELH_ENGED: {felhasznalo, engedely}

ENGEDELYEK: engedély kódja és elnevezése
ENGEDELYEK: {engedely_kod, elnevezes}
  

3.3	A felhasználói felület
Törekedtem az egyszerűségre, hogy a program kezelését ne kelljen megtanulni és az átlag felhasználók is könnyen használhassák a programot. Továbbá a kezelőfelület egyértelmű, a feliratok nem félrevezetőek, így könnyen értelmezhető, hogy mi mit működtet, minek a hatására mi történik. Próbáltam egy logikus, átlátható felületet megtervezni, ahol az egyes vezérlőelemek jól elkülönülnek egymástól. A program mindig csak annyi információt szolgáltat, ami feltétlenül szükséges, így nem válik bo-nyolulttá a működés. Az átláthatóság és a logikus felhasználói felület megalkotása ér-dekében, ahol szükséges volt a felület nagysága miatt a JTabbedPane komponenst sze-retném használni, mivel az ügyfél az 1024*768 pixeles felbontást igényelte, ezért min-den felületen egységesen alkalmaztam.
A program a bejelentkező felülettel indul, ahol a felhasználó be tud jelentkezni, miután belépett a felhasználó, átkerül a főmenübe, ahol ki tudja választani, mit szeretne csi-nálni. A főmenüben négy gomb található, ahonnan a következő menüpontokra lehet gombnyomásra átugrani. A járműkereskedés járműveit a járművek panelen lehet kivá-lasztani. A járművek hozzáadása panelen járműveket lehet hozzáadni az adatbázishoz. A szerviz panelen pedig az ügyfél által behozott járművek szervizelésének menetét lehet elindítani.

3.3.1	Bejelentkezés
Az alkalmazás ezzel a felülettel indul, ahol a felhasználó betud jelentkezni. Vélemé-nyem szerint ennek a felületnek az egyszerűség és letisztultság a lényege, ezért ennek a gondolatmenetnek megfelelően terveztem meg. A felhasználónak meg kell adnia a felhasználónevét, és a jelszavát a bejelentkezéshez. Miután sikeresen bejelentkezett, átkerül a főmenübe.


3.3.2	Főmenü
A főmenü arra szolgál, hogy az alkalmazás funkcióit egy helyről el tudjuk érni. Ebből a menüpontból tudunk elnavigálni a többi menübe, ezenkívül kijelzi a pontos időt.

	
3.3.3	Járművek keresése
Ebben a menüpontban járművek szerint lehet keresni. A felületet a JTabbedPane kom-ponens használatával három részre bontottam. Mind a három járműtípusnál azonosak a keresési feltételek. A keresési feltételek a következőek, márka, modell, típus, kivitel, állapot, üzemanyag, rendszám, adott évjárattól, adott évjáratig, adott hengerűrtarta-lomtól, adott hengerűrtartalomig, adott kilométeróra állástól, adott kilométeróra állá-sig, adott vételártól, adott vételárig. Miután megadtuk a kívánt keresési paramétereket, egy új panelen megjelenek a járművek. Jelen pillanatban a keresés nem működik ezért, ha a felhasználó a keresés gombra kattint, a keresés feltételétől függetlenül mindegyik jármű adata megjelenik.



3.3.4	Járművek hozzáadása
A járművek hozzáadása menüpontban, hozzá tudjuk adni a kiválasztott jármű típus alapján az adott járművet. A menüpontot három részre osztottam a JTabbedPane kom-ponens használatával. Ezenkívül ebben a menüpontban lehet a jármű adatait megvál-toztatni, vagy adott járművet törölni. A jármű módosítása ablaka három részből áll, ezek az ablakok hasonlítanak a jármű hozzáadás ablakhoz. Az első részben A jármű rendszámát kell megadni, mivel ez határozza meg az autót. Miután a rendszám kitöl-tésre került a következő adatokat lehet megváltoztatni: jármű márkája, modellje, típu-sa, gyártási évjárata és hónapja, kivitele, és állapota. A második részben meg lehet változtatni, hogy az adott jármű milyen típusú üzemanyagot használ a működéséhez, ezenkívül a jármű hengerűrartalmát, kilométeróra állását, saját tömegét és teljesítmé-nyét. A harmadik ablakban a jármű színét, sebesség váltó fajtáját, leírását, hibá-it/típushibáit, a jármű vételárát és a státuszát, hogy éppen a telephelyen van-e az adott jármű.



A 7.-ábra a jármű módosításának az átláthatósági ábrája.






3.3.5	Szerviz
A legösszetettebb menü az alkalmazásban. Új munkalap létrehozására szolgál. Új munkalap létrehozásakor a következő dolgokat kell megadni: ügyfél adatai, márka kódja és elnevezése (ha esetleg nem tartalmazná az adott márkát az adatbázis), autó adatai, munkalap, feladat, szerelés (adott munkalap szerelési információ), alkatrész és beszerzés. Miután a felhasználó kitöltötte a fent említett menüpontokat, a beszerzés fülön található munkalap készít gomb megnyomásával munkalapot generál a megadott adatok szerint.
















3.4	Felhasználó hozzáadása
A menüpontban új felhasználót lehet hozzáadni, mindegyik mező kitöltése kötelező.

3.5	Szerviz módosítása
A szerviz módosítása menüpontba az ügyfél adatait, és a beszerzéshez kapcsolatos adatokat lehet megváltoztatni.




3.6	MVC
A program az MVC architekturális tervezési minta alapján került elkészítésre. Ebben a tervezési eljárásban a fejlesztő külön részekre választja az alkalmazását. Ezek a részek a model, view és a controller. Magyarul a modell, nézet és a vezérlő. Minden résznek megvan a maga feladatköre és szerepe.
•	A modell az adatok tárolására.
•	A nézet a képernyőn való megjelenítésre
•	A vezérlő a belépési pont és biztosítja az eseménykezelést.
A felhasználó által indított eseményt a vezérlő kezeli. Ilyen esemény lehet például egy gomb megnyomása. A vezérlő eldönti, hogy megváltoztatja-e a modellt vagy a nézetet, esetleg mind a kettőt. Amennyiben a modellt megváltoztatta, abban az esetben az érte-sítést küld a nézetnek a változásról. Ezen értesítés hatására olvassa ki a nézet a mo-dellből a megváltozott adatot, majd megjeleníti azt. A 24. ábra az MVC-modell mű-ködése (forrás: MVC_mintak.ppt) az én programomhoz való működését ábrázolja.

 
3.6.1	UML ábra
Az alkalmazás teljes diagramját a következő linken keresztül lehet megtekinteni.
Járműkezelő Szoftver UML diagram
 
 
4.	Megvalósítás
A bevezetés, a feladat specifikáció, és a tervezés meghatározása után következzen a megvalósítás néhány részlete. Négy nagyobb részre bontanám a megvalósítást. Ezeket a részeket lennebb fejtem ki. A forráskódot megpróbálom abban a sorrendben bemu-tatni, ahogy az alkalmazás működése során feldolgozásra kerül. A változók, metódusok és objektumok névadásánál törekedtem a könnyen érthetőségre, és arra, hogy az azo-nosítók valamennyire kifejezzék a programban betöltött szerepüket. A dolgozatban a legfontosabb, legnehezebb részeket emelem ki, ami alapján a program működése meg-érthető. A programot a Main osztály indítja el, ez az osztály felelős a Vezérlő osztály létrehozásáért. A Vezérlő osztály elindítja a Modell és a Nézet osztályt. A Modell osz-tály tartalmazza a POJO (Plain Old Java Object) osztályokat. A POJO egy olyan Java-objektum, amelyet nem köt semmiféle korlátozás, azokon kívül, amelyek a Java nyelvi specifikációjából adódnak. Ezenkívül az adatbázissal való kapcsolati metódusokat tar-talmazza. A Nézet osztály tartalmazza a felhasználói felület elemeit, és megadja, hogy nézzen ki a program, amit a felhasználó lát. Ebben az osztályban találhatóak a szöve-ges mezőket validáló metódusok, és az osztályban szereplő komponensek eléréséhez szükséges metódusok(getter).
4.1	Járművek hozzáadásának megvalósítása
Járműhozzáadása / módosítása menüben lehet új járművet hozzá adni. Miután a fel-használó kiválasztotta a cmbJarmuTipusa JComboBox nevű komponenssel az adott járműtípust, megadhatja a felvinni kívánt jármű adatait. Mikor megnyomja a „Jármű hozzáadása” nevű gombot a felhasználó, akkor először is lefutnak sorban a következő validáló metódusok: „JarmuHozzRendszamValidalas(), JarmuHozzMarkaVa-lidalas(), JarmuHozzModellValidalas(), JarmuHozzTipusValidalas(), JarmuHozzLeirasValidalas(), JarmuHozzHibaValidalas(), Jarmu-HozzHengerValidalas(), JarmuHozzKilometerValidalas(), Jarmu-HozzTeljesitmenyValidalas(), JarmuHozzArValidalas(), JarmuHozs-zSajatTomegValidalas().” Minden esetben, mikor a beírt adatt nem egyezik a megadott feltételnek, egy felugró ablakban jelzi a felhasználó felé, hogy javítsa ki. Miután minden adatott helyesen adott meg a felhasználó, akkor a kiválasztott jármű típusa alapján három a vezérlő osztályban található metódus hívódik meg. Mindegyik esetben meghívódik a „jarmuHozzaAdasaSzovegesMezokTorlese()” nevű metó-dus. Ha a személyautó van kiválasztva akkor a „jarmuAutoAdatokFeltolt()” nevű metódus indul el. Amikor a motorkerékpár van kiválasztva akkor a „jarmuMotorAda-tokFeltolt()” nevű metódus indul el. Egyébként a „jarmuKamionAdatokFel-tolt()” nevű metódus hívódik meg. Mind a három metódusnak a működési elve ugyan az, a szöveges mezőkbe beírt és az éppen kiválasztott tartalom a lenyíló mezők-ből egy segédlistán keresztül átadja a modell osztályban levő „szemelyautoAdatok-Feltolt()” nevű metódusnak az értékeket. Az átadott értékek kerülnek fel új adat-ként az adatbázisba.
4.2	Járművek megjelenítésének megvalósítása
A járművek feliratú gombot kell a felhasználónak megnyomni, hogy eljusson ebbe a menüpontba. Mindhárom jármű típushoz tartozik egy panel. Mindegyik panelen talál-ható egy keresés gomb, amit megnyomva megjelenik a keresett jármű találati tábláza-ta, a találati panelben, melyek kereséstől függetlenül jelnek meg az adatbázisban tárolt járművek szerint.
4.3	Szerviz működésének megvalósítása
A szerviz menüpontot nyolc panelre van osztva az átláthatóság és könnyebb kezelés érdekében. Mindegyik panelen található egy „felvitel” nevű gomb, mely panelektől függetlenül ugyan azt a célt szolgálja, az adatok felvitelét az adatbázisba, és a meg-adott paramétereket egy listába ömleszti, majd a beszerzés panelen található „Munka-lap készít” nevű gomb megnyomásával PDF dokumentumot generál a felhasználó által beírt adatok alapján. A felvitel gomb megnyomás után elindulnak a validáló metódu-sok, melyek az adott felülettől függetlenül mindegyik szöveges mező tartalmát meg-vizsgálják az éppen aktuális panelen természetesen. Az ügyfél felvitelénél használt validáló metódusok a következőek: „SzervizUgyfelNevValida-las(),SzervizUgyfelLakcimValidalas(),SzervizUgyfelElerhetosegValida-las(),SzervizUgyfelOkmanyTipusValidalas(),SzervizUgyfelOkmanyAzonValidalas()”. Ha mindegyik szöveges mező tartalma megfelel a validálásnak, akkor elindul a vezérlő osztályban található „ugyfelAdatokFeltolt()” nevű metódus. A modell osztályban található „ugyfelAdatokFeltolt()” nevű metódus a „ugyfel-Lista()” nevű metódus segítségével feltölti a felhasználó által megadott adatokat.
4.4	Felhasználók kezelése
Az alkalmazás a bejelentkezési ablakkal indul, ahol a beírt felhasználónevet és jelszót ellenőrzi a tárolt felhasználónévvel és jelszóval. A belépés gombbal indul el az ese-mény, ami ellenőrzi az adatokat. A belépés gombra kattintva elindul egy esemény, amely először ellenőrzi a mezők tartalmát. Mikor a mezők tartalma üres, értesíti a fel-használót erről. Ezek után ellenőrzi a vezérlő osztályban található „belephet()” ne-vű metódus, hogy a megadott felhasználónév és jelszó egyezik-e az adatbázisban tárólt felhasználónévvel és jelszóval. Abban az esetben, mikor a felhasználó helyesen adta meg a bejelentkezési adatokat, akkor a „belepes()” nevű metódus hívódik meg, mely tovább irányítja a felhasználót a főmenübe. Miután az Admin belépett az alkal-mazásba, a felhasználói fiókok kezelése nevű menübe lépve, új felhasználót tud hozzá adni. Mikor a felhasználó megnyomja a Felvitel nevű gombot, elindul a szöveges me-zők validálása a következő nevű metódusokkal az adott sorrendben: „vezetekNevVa-lidalas(), keresztNevValidalas(), regFelhasznaloNevValidalas(), regJelszoValidalas, regJelszoUjraValidalas()”. Ezek után ellenőrzi a program, hogy a két megadott jelszó egyezik-e. Mikor egyeznek a jelszavak és minden mező értéke megfelel a validálásnak, akkor lefut a vezérlő osztályban levő „felhasz-naloAdatokFeltolt()” nevű metódus, és vissza jelzi a felhasználónak egy felugró ablakban, hogy sikeres volt a felhasználó felvitele. A „felhasznaloAdatokFel-tolt()” nevű metódus a „felhasznaloLista()” nevű metódus segítségével értékül adja a megadott adatokat a modell osztályban található „felhasznaloAdatokFel-tolt()” nevű metódusnak. A metódus beszúrja a megadott adatokat az adatbázisba.
4.5	Osztályok leírása
4.5.1	Main
A Main osztály a program belépési pontja. Ez az osztály indítja el a vezérlő osztályt, ami a nevéből is kiindulva vezérli a programot.
Metódusok:
void Main(): A metódus indítja el a programot, és deklarálja a Vezérlő osztályt.

4.5.2	Adatbázis
Az adatbázis osztály egy interfész, ami tartalmazza a Modell osztályhoz használt le-kérdezéseket, adat beszúrásokat, frissítéseket és törléseket.
Attribútumok:

String IP_LOCAL: A szerver fajtája.

String DATABASE_NAME: Az adatbázis neve.

String DRIVER: Az adatbázishoz való csatlakozási driver megnevezése.

String URL: Az adatbázishoz való csatlakozási URL.

String USER: Az adatbázishoz való csatlakozási felhasználónév.

String PASSWORD: Az adatbázishoz való csatlakozási jelszó.

String SQL_FELHASZNALO_ADATOK: A felhasználó nevű táblából lekérdezi a fel-használók kódját, vezetéknevét, keresztnevét, felhasználónevét és jelszavát.

String SQL_SZERVIZ_MARKA: A márka nevű táblából lekérdezi a márka kódját és elnevezését, ezenkívül rendezi a lekérdezett sorokat az elnevezés szerint.

String SQL_UGYFEL: Az ügyfél nevű táblából lekérdezi az ügyfél személy azonosí-tó számát, típusát, nevét, elérhetőségét és lakcímét, ezenkívül rendezi a lekérdezett sorokat az ügyfelek neve alapján.

String SQL_RENDSZAM: Az autó nevű táblából lekérdezi a rendszámokat, és rendezi a sorokat a rendszámok szerint.

String SQL_MUNKALAP_SZAM: A munkalap nevű táblából lekérdezi a munkalap számot, és rendezi a sorokat a munkalap szám alapján.
String SQL_DOLGOZO: A dolgozók nevű táblából lekérdezi a dolgozók kódját és nevét, és rendezi a sorokat a dolgozók neve szerint.

String SQL_FELADAT: A feladat nevű táblából lekérdezi a feladat kódját és az elne-vezését, ezenkívül rendezi a sorokat a feladat kódja alapján.

String SQL_BESZERZES_MUNKALAP_SZAM: A szerelés nevű táblából lekérdezi a munkalap számát, és rendezi a sorokat a munkalap szám szerint.

String SQL_ALKATRESZ: Az alkatrész nevű táblából lekérdezi az alkatrészek meg-nevezését, és rendezi a sorokat a megnevezés alapján.

String SQL_AUTO_ADATOK_MODOSIT_TABLAZAT: Minden adatot lekérdez a keres-kedés autó táblából.

String SQL_AUTO_MARKA: Az összes adatbázisban tárolt márkát lekérdezi a keres-kedés autó táblából. Motorkerékpárokra és haszonjárművekre is van ilyen lekérdezés, de mivel használatukat tekintve megegyeznek nem fejtem ki bővebben őket.

String SQL_AUTO_UZEMANYAG: Az összes adatbázisban tárolt üzemanyag fajtát lekérdezi a kereskedés autó táblából. Motorkerékpárokra és haszonjárművekre is van ilyen lekérdezés, de mivel használatukat tekintve megegyeznek nem fejtem ki bőveb-ben őket.

String SQL_AUTO_KIVITEL: Az összes adatbázisban tárolt jármű kivitelt lekérdezi a kereskedés autó táblából. Motorkerékpárokra és haszonjárművekre is van ilyen le-kérdezés, de mivel használatukat tekintve megegyeznek nem fejtem ki bővebben őket.

String SQL_AUTO_ALLAPOT: Az összes adatbázisban tárolt jármű állapotot lekér-dezi a kereskedés autó táblából. Motorkerékpárokra és haszonjárművekre is van ilyen lekérdezés, de mivel használatukat tekintve megegyeznek nem fejtem ki bővebben őket.

String SQL_INSERT_FELHASZNALO: A felhasználó nevű táblába szúrja be a fel-használó vezetéknevét, keresztnevét, felhasználónevét és jelszavát.

String SQL_INSERT_UGYFEL: Az ügyfél nevű táblába szúrja be az ügyfél személy-azonosító okmány számát, típusát, nevét, elérhetőségét és lakcímét.

String SQL_INSERT_MARKA: A márka nevű táblába szúrja be a márka kódját és elnevezését.

String SQL_INSERT_AUTO: Az autó nevű táblába szúrja be az autó rendszámát, ügyfél azonosító számát, az autó márkáját, évjáratát, típusát, színét, motorszámát és az alvázszámát.

String SQL_INSERT_MUNKALAP: A munkalap nevű táblába szúrja be a munkalap számát, az autó rendszámát, a hiba jegyzéket, munkadíját, anyagdíját és a vállalási határidőt.

String SQL_INSERT_FELADAT: A feladat nevű táblába szúrja be a feladat kódját, a munkadíjat, a feladat elnevezését és típusát.

String SQL_INSERT_SZERELES: A szerelés nevű táblába szúrja be a szerelés mun-kalap számát, a hozzá rendelt szerelőt, a hozzá rendelt feladatot, a szerelés kezdési idejét, a szerelés befejezési idejét és a szereléssel eltöltött munkaórák számát.

String SQL_INSERT_ALKATRESZ: Az alkatrész nevű táblába szúrja be az alkatrész kódját és megnevezését.

String SQL_INSERT_BESZERZES: A beszerzés nevű táblába szúrja be a beszer-zési kódot, a beszerzéshez hozzárendelt munkalap számát, az alkatrész kódját, az al-katrész megrendelési árát és a megrendelési idejét.

String SQL_INSERT_KERESKEDES_AUTO: A kereskedés autó nevű táblába szúrja be a személyautó rendszámát, márkáját, modelljét, típusát, a gyártási évjáratát, a gyártási hónapját, a kivitelét, az állapotát, az üzemanyagát, a hengerűrtartalmát, kilométeróra állását, a saját tömegét, a teljesítményét, a színét, a sebességváltó fajtáját, a személy-autó rövid leírását, a személyautó hibáit, azt, hogy éppen a telephelyen van-e és a vé-telárát.
A motor és kamion táblákra is van insert parancs, mivel használatukat tekintve meg-egyeznek, nem fejtem ki bővebben őket.

String SQL_UPDATE_KERESKEDES_AUTO: A kereskedés autó nevű tábla sorait fris-síti az adott adattokkal. Motorkerékpárokra és haszonjárművekre is van ilyen lekérde-zés, de mivel használatukat tekintve megegyeznek, nem fejtem ki bővebben őket.

String SQL_DELETE_KERESKEDES_AUTO: A kereskedés autó nevű tábla adott sorát törli ki a parancs. Motorkerékpárokra és haszonjárművekre is van ilyen lekérdezés, de mivel használatukat tekintve megegyeznek, nem fejtem ki bővebben őket.

String SQL_UPDATE_UGYFEL_ADATOK: Az ügyfél nevű tábla sorait frissíti az adott adattokkal.

String SQL_BESZERZES_ADATOK_TABLAZAT: A beszerzés nevű táblából lekérdezi a beszerzés kódját, munkalap számot, alkatrészt, az alkatrész árát, és a megrendelés idejét, ezenkívül rendezi a lekérdezett sorokat a beszerzés kódja alapján.

String SQL_UPDATE_BESZERZES_ADATOK: A beszerzés nevű tábla sorait frissíti az adott adatokkal.

4.5.3	Modell
A Modell osztály felelős az adatok lekérdezésért, felviteléhez és módosításához. Az adatbázis interfész implementálva van a modell osztályhoz. Az interfészben lévő le-kérdezések alapján az implementáló osztály vezérlő metódusai férnek hozzá a modell-ben tárolt adatokhoz.
Attribútumok:
String jelszo: Felhasználó jelszó feltöltéséhez szükséges segéd változó, ezt a vál-tozót a jelszoTitkositas metódus hívja meg.
String modellTitkositottJelszo: A jelszoTitkositas metódus vissza téréi érté-ke, ennek az adattagnak az értéké töltöm fel az adatbázisba.

boolean vanDriver: A driver betöltéséhez szükséges változó.

File htmlFajl: Az index.html fájl létrehozásáért felelős változó.

String html: A HTML fájl egy string típusú adatt mely, tartalmazza a weboldal alap elemeit.

Metódusok:
boolean driverBetolt(): A metódus betölti a „DRIVER” nevű változót.

void HTMLFajltGeneral(String, String): A metódus a két paraméter alap-ján: fejléc és az adat, generálja az adatokat, és hozzá adja a „html„ nevű változóhoz.

ArrayList<Felhasznalo> belepesAdatokFeltolt(): Új felhasználó adatai-nak feltöltése az adatbázisba.

ArrayList<SzervizMarka> getSzervizMarkaLista(): A metódus a márka nevű táblából kérdezi le a márkák kódját és elnevezését.

ArrayList<Ugyfel> getUgyfelLista(): A metódus az ügyfél nevű táblából kérdezi le a személyazonosító okmány számát, személyazonosító okmány típusát, az ügyfél nevét, elérhetőségét, lakcímét.

ArrayList<Rendszam> getRendszamLista(): A metódus az autók nevű táblá-ból kérdezi le a létező autók rendszámokat.

ArrayList<MunkalapSzam> getMunkalapSzamLista(): A metódus a munkalap nevű táblából kérdezi le az összes létező munkalap számokat.

ArrayList<Dolgozo> getDolgozoLista(): A metódus a dolgozó nevű táblából kérdezi le a dolgozók kódját és nevét.
ArrayList<Feladat> getFeladatLista(): A metódus a feladat nevű táblából kérdezi le az összes feladat kódját, és elnevezését.

ArrayList<MunkalapSzam> getBeszerzesMunkalapSzamLista(): A metódus a szerelés nevű táblából kérdezi le az összes munkalap számot.

ArrayList<Alkatresz> getAlkatreszLista(): A metódus az alkatrész nevű táblából kérdezi le az összes alkatrész megnevezését.

ArrayList<Marka> getAutoMarkaLista(): A metódus a kereskedés autó nevű táblából kérdezi le az összes autó márkáját. Motorkerékpárokra és haszonjárművekre is van ilyen metódus, de mivel használatukat tekintve megegyeznek, nem fejtem ki bő-vebben őket.

ArrayList<Uzemanyag> getAutoUzemanyagLista(): A metódus a kereskedés autó nevű táblából kérdezi le az összes létező autóhoz tartozó üzemanyag típusokat. Motorkerékpárokra és haszonjárművekre is van ilyen metódus, de mivel használatukat tekintve megegyeznek, nem fejtem ki bővebben őket.

ArrayList<Kivitel> getAutoKivitelLista(): A metódus a kereskedés autó nevű táblából kérdezi le az összes létező autóhoz tartozó kiviteli lehetőségeket. Motor-kerékpárokra és haszonjárművekre is van ilyen metódus, de mivel használatukat te-kintve megegyeznek, nem fejtem ki bővebben őket.

ArrayList<Allapot> getAutoAllapotLista(): A metódus a kereskedés autó nevű táblából kérdezi le az összes létező autóhoz tartozó állapot lehetőségeket. Motor-kerékpárokra és haszonjárművekre is van ilyen metódus, de mivel használatukat te-kintve megegyeznek, nem fejtem ki bővebben őket.

void felhasznaloAdatokFeltolt(ArrayList<String>): A lista elemeit tölti fel az adatbázisba, mielőtt a jelszó adattagot feltöltené, előbb át adja a „jelszoTitko-sitas()” nevű metódusnak. A metódus vissza térési értékét töltöm fel az adatbázisba.

void ugyfelAdatokFeltolt(ArrayList<String>): A felhasználó által meg-adott szerviz menüben lévő ügyfél panel adatait tölti fel az adatbázisba.

void ugyfelAdatokModosit(ArrayList<String>): A felhasználó által módo-sított, a szerviz módosítás menüben lévő ügyfél adatait frissíti az adatbázisban.

void markaAdatokFeltolt(ArrayList<String>): A felhasználó által meg-adott szerviz menüben lévő márka panel adatait tölti fel az adatbázisba.

void autoAdatokFeltolt(ArrayList<String>): A felhasználó által megadott szerviz menüben lévő autó panel adatait tölti fel az adatbázisba.

void munkalapAdatokFeltolt(ArrayList<String>): A felhasználó által megadott szerviz menüben lévő munkalap panel adatait tölti fel az adatbázisba.

void feladatAdatokFeltolt(ArrayList<String>): A felhasználó által meg-adott szerviz menüben lévő feladat panel adatait tölti fel az adatbázisba.

void szerelesAdatokFeltolt(ArrayList<String>): A felhasználó által megadott szerviz menüben lévő szerelés panel adatait tölti fel az adatbázisba.

void alkatreszAdatokFeltolt(ArrayList<String>): A felhasználó által megadott szerviz menüben lévő alkatrész panel adatait tölti fel az adatbázisba.

void beszerzesAdatokFeltolt(ArrayList<String>): A felhasználó által megadott szerviz menüben lévő beszerzés panel adatait tölti fel az adatbázisba.

void beszerzesAdatokModosit(ArrayList<String>): A felhasználó által módosított, a szerviz módosítás menüben lévő beszerzés adatait frissíti az adatbázis-ban.

void szemelyautoAdatokFeltolt(ArrayList<String> ): A  felhasználó által megadott jármű hozzáadása menüpontban levő adatokat tölti fel az adatbázisba.
Motorkerékpára, és a haszonjárművekre is van adatok feltölt metódus, de mivel műkö-désüket tekintve megegyeznek nem fejtem ki őket.
void szemelyautoAdatokModosit(ArrayList<String> ): A  felhasználó által módosított jármű módosítás/törlés menüpontban levő adatokat frissíti az adatbá-zisban. Motorkerékpára, és a haszonjárművekre is van adatok módosít metódus, de mivel működésüket tekintve megegyeznek nem fejtem ki őket.

void szemelyautoAdatokTorol(String): A  felhasználó által tőrölt jármű mó-dosítás/törlés menüpontban levő adatokat törli az adatbázisban. Motorkerékpára, és a haszonjárművekre is van törlési metódus, de mivel működésüket tekintve megegyez-nek nem fejtem ki őket.

String jelszoTitkositas(String, String): A  metódusnak két bemenő pa-ramétere van: az első paraméter a megadott jelszót, melyet titkosít a metódus, a máso-dik paraméter pedig a salt. A metódus visszatérési értéke a tikosított jelszó.

DefaultTableModel getAutoAdatok(): A metódus a kereskedés autók nevű táblából kérdezi le az össze adatot, majd átalakítja a lekérdezett adatokat, olyan mó-don, hogy azokat táblázat formájába meg tudja jeleníteni. Motorkerékpára, és a ha-szonjárművekre is van adatok megjelenítés táblázatos formában metódus, de mivel működésüket tekintve megegyeznek nem fejtem ki őket.

DefaultTableModel getUgyfelAdatokTablazat(): A metódus az ügyfél nevű táblából kérdezi le az össze adatot, majd átalakítja a lekérdezett adatokat, olyan mó-don, hogy azokat táblázat formájába meg tudja jeleníteni.

DefaultTableModel getBeszerzesAdatokTablazat(): A metódus a beszerzés nevű táblából kérdezi le az össze adatot, majd átalakítja a lekérdezett adatokat, olyan módon, hogy azokat táblázat formájába meg tudja jeleníteni.

A Modell osztály tartalmazz több POJO osztályt, ezek a következőek: Felhasznalo, SzervizMarka, Marka, Ugyfel, Kedvezmeny, Kivitel, Uzemanyag, Al-lapot, Rendszam, Munkalapszam, Dolgozo, Feladat, Alkatresz.
Mindegyik osztály tartalmazz a szükséges adattagokat, konstruktorokat, gettereket, és ToString metódusokat.

4.5.4	Vezérlő
A vezérlő osztály irányítja a programot. Ez az osztály indítja el a modell és a nézet osztályt.
Attribútumok:
Modell modell: A modell osztály példányosításához szükséges adattag.

Nezet nezet: A nézet osztály példányosításához szükséges adattag.

Timer t: Az időzítőhöz szükséges adattag.

Metódusok:
void setAutoAdatokTablazat(): A nézet osztályban található járművek módosí-tása táblázatnak adja át a modell osztályban lévő „getAutoAdatok()” nevű metó-dust. A motorkerékpárokra, és a haszonjárművekre is van táblázat beállító metódus, de mivel működésüket tekintve megegyeznek nem fejtem ki őket.

ArrayList<String> felhasznaloLista(String, String, String, String): Felhasználó segédlista az adatbázisba feltöltött adatokhoz.

ArrayList<String> ugyfelLista(String, String, String, String, String): Ügyfél segédlista az adatbázisba feltöltött adatokhoz.

ArrayList<String> markaLista(String, String): Márka segédlista az adat-bázisba feltöltött adatokhoz.

ArrayList<String> autoLista(String, String, String, String, String, String, String, String): Autó segédlista az adatbázisba feltöltött adatokhoz.

ArrayList<String> munkalapLista(String, String, String, String, String , String): Munkalap segédlista az adatbázisba feltöltött adatokhoz.

ArrayList<String> feladatLista(String, String, String, String): Feladat segédlista az adatbázisba feltöltött adatokhoz.
ArrayList<String> szerelesLista(String, String, String, String, String, String): Szerelés segédlista az adatbázisba feltöltött adatokhoz.

ArrayList<String> alkatreszLista(String, String): Alkatrész segédlista az adatbázisba feltöltött adatokhoz.

ArrayList<String> beszerzesLista(String, String, String, String, String): Beszerzés segédlista az adatbázisba feltöltött adatokhoz.

ArrayList<String> szemelyautoLista(String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String, String): Személyautó segédlista az adatbázisba feltöltött adatokhoz. Motorkerékpárra és ha-szonjárműre is van ilyen metódus, de mivel működésüket tekintve megegyeznek, nem fejtem ki őket.

void felhasznaloAdatokFeltolt(): Felhasználók adatainak adatbázisba való feltöltéséhez szükséges vezérlő metódusa.

void ugyfelAdatokFeltolt(): Ügyfelek adatainak adatbázisba való feltöltésé-hez szükséges vezérlő metódusa.

void ugyfelAdatokModosit(): Ügyfelek adatainak adatbázisban való módosítá-sához szükséges vezérlő metódusa.

void markaAdatokFeltolt(): Szervizben levő autók márka adatainak adatbázis-ba való feltöltéséhez szükséges vezérlő metódusa.

void autoAdatokFeltolt(): Szervizben levő autók adatainak adatbázisba való feltöltéséhez szükséges vezérlő metódusa.

void munkalapAdatokFeltolt(): Szervizben levő munkalap adatainak adatbá-zisba való feltöltéséhez szükséges vezérlő metódusa.
void feladatAdatokFeltolt(): Szervizben levő feladat adatainak adatbázisba való feltöltéséhez szükséges vezérlő metódusa.

void szerelesAdatokFeltolt(): Szervizben levő szerelés adatainak adatbázisba való feltöltéséhez szükséges vezérlő metódusa.

void alkatreszAdatokFeltolt(): Szervizben levő alkatrész adatainak adatbá-zisba való feltöltéséhez szükséges vezérlő metódusa.

PdfPCell cella(String, int, boolean, int): A PDF generáláshoz  szüksé-ges PDF-ben található cellák tulajdonságainak beállításához szükséges metódus.

void pdfGeneral(): Munkalapot generál PDF formátumba a Szerviz menü adott ügyfél, autó, munkalap, feladat, szerelés és beszerzés adatai alapján.

void beszerzesAdatokFeltolt(): Szervizben levő beszerzés adatainak adatbá-zisba való feltöltéséhez szükséges vezérlő metódusa.

void jarmuAutoAdatokFeltolt(): Jármű hozzáadása menüben való járművek adatainak feltöltéséhez szükséges vezérlő metódusa. Motorkerékpárra és haszonjármű-re is van ilyen metódus, de mivel működésüket tekintve megegyeznek, nem fejtem ki őket.

void jarmuAutoAdatokModosit(): Jármű módosítás/törlés menüben való jármű-vek adatainak módosításához szükséges vezérlő metódusa. Motorkerékpárra és haszon-járműre is van ilyen metódus, de mivel működésüket tekintve megegyeznek, nem fej-tem ki őket.

void jarmuAutoAdatokTorol(): Jármű módosítás/törlés menüben való járművek adatainak törléséhez szükséges vezérlő metódusa. Motorkerékpárra és haszonjárműre is van ilyen metódus, de mivel működésüket tekintve megegyeznek, nem fejtem ki őket.

boolean belephet(): A belépés panelben megadott felhasználónevet és jelszót hasonlítja össze az adatbázisban tárolt felhasználónévvel és jelszóval.

String ido(): A főmenüben megjelenő pontos idő kijelzéséhez szükséges metó-dus.

DefaultComboBoxModel szervizMarkaListaKe-szit(ArrayList<SzervizMarka>): Kivitel lista készítéséhez szükséges metódus. A kivitelekhez használt legördülő menübe való adatokat adja át.

DefaultComboBoxModel ugyfelListaKeszit(ArrayList<Ugyfel>): Ügyfél lista készítéséhez szükséges metódus. Az ügyfelekhez használt legördülő menübe való adatokat adja át.

DefaultComboBoxModel rendszamListaKeszit(ArrayList<Rendszam>): Rendszám lista készítéséhez szükséges metódus. A rendszámokhoz használt legördülő menübe való adatokat adja át.

DefaultComboBoxModel 
munkalapSzamListaKeszit(ArrayList<MunkalapSzam>): Munkalapszám lista készítéséhez szükséges metódus. A munkalapszámokhoz használt legördülő menübe való adatokat adja át.

DefaultComboBoxModel dolgozoListaKeszit(ArrayList<Dolgozo>): Dol-gozó lista készítéséhez szükséges metódus. A dolgozókhoz használt legördülő menübe való adatokat adja át.

DefaultComboBoxModel feladatListaKeszit(ArrayList<Feladat>): Fel-adat lista készítéséhez szükséges metódus. A feladatokhoz használt legördülő menübe való adatokat adja át.
DefaultComboBoxModel alkatreszListaKeszit(ArrayList<Alkatresz>): Alkatrész lista készítéséhez szükséges metódus. Az alkatrészekhez használt legördülő menübe való adatokat adja át.
DefaultComboBoxModel 

ugyfelModositListaKeszit(ArrayList<Ugyfel>): Az ügyfél módosítása lista készítéséhez szükséges metódus. Az ügyfél módosításához használt legördülő menübe való adatokat adja át.

void jarmuKeresesAuto(): A nézet osztályban található találati táblázatnak adja át a modell osztályban lévő „getAutoAdatok()” nevű metódust. Motorkerékpárra és haszonjárműre is van ilyen metódus, de mivel működésüket tekintve megegyeznek, nem fejtem ki őket.

4.5.5	Nézet
A Nézet osztály valósítja meg a GUI-t és az adatok megjelenítését a különböző kom-ponensek használatával. Az implicit getter és setter metódusok nem kerültek jelölésre az átláthatóság fenntartása érdekében. Mindazonáltal néhány metódust leírok, mert véleményem szerint, a működésüket, és a hasznosságukat tekintve kiemelkedő szere-pük van a programban.
Attribútumok:

Vezerlo vezerlo: A vezérlő osztály példányosításához szükséges adattag.

ImageIcon autoKep: Jármű keresés panelben lévő személyautó panel képe.

ImageIcon motorKep: Jármű keresés panelben lévő motorkerékpár panel képe.

ImageIcon kamionKep: Jármű keresés panelben lévő haszonjármű panel képe.


ImageIcon felhasznaloKep: Bejelentkezés panelen lévő felhasználóhoz tartozó kép.

ImageIcon jelszoKep: Bejelentkezés panelen lévő jelszóhoz tartozó kép.

JFileChooser openFile: Kép kiválasztására szolgáló fájlkiválasztó adattag.

JFileChooser saveFile: Kép mentésére szolgáló fájlkiválasztó adattag.

BufferedImage bf: Kép adattag.

String jelszo: Jelszó adattag, amit a bejelentkezéshez és a jelszó titkosításhoz használok.

String[] autoKivitel: String típusú tömb, ami tartalmazza az összes személyau-tó kivitel típusokat.

String[] motorKivitel: String típusú tömb, ami tartalmazza az összes motorke-rékpár kivitel típusokat.

String[] kamionKivitel: String típusú tömb, ami tartalmazza az összes haszon-jármű kivitel típusokat.

String[] motorUzemanyag: String típusú tömb, ami tartalmazza az összes motor-kerékpárhoz tartozó üzemanyag típusokat.

String[] autoKamionUzemanyag: String típusú tömb, ami tartalmazza az összes személyautóhoz és haszonjárműhöz tartozó üzemanyag típusokat.

Metódusok:

void setLblIdoKijelzes(): A vezérlő osztály „ido()” nevű metódusát adja értélük a „lblIdoKijelzes” nevű adattagnak.

boolean SzervizUgyfelNevValidalas(): Boolean típusú metódus, ami a „txtUgyfelNev” nevű szöveges mező értékét ellenőrzi a következő minta alapján: „^[A-Za-z\\sÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,50}$”.

boolean SzervizMunkalapSzamValidalas(): Boolean típusú metódus, ami a „txtMunkMunkalap” nevű szöveges mező értékét ellenőrzi a következő minta alap-ján: „^mu[\\/]{1}(?:19|20)\\d{2}[\\/]{1}[\\d]{4}$”.

boolean SzervizMunkalapValHataridoValidalas(): Boolean típusú metó-dus, ami a „txtMunkHatarido” nevű szöveges mező értékét ellenőrzi a következő minta alapján: „^(?:((?:19|20)[0-9]{2})[\\/\\-. ]?(?:(0[1-9]|1[0-2])[\\/\\-. ]?([0-2][1-8]|[12]0|09|19)|(0[13-9]|1[0-2])[\\/\\-. ]?(29|30)|(0[13578]|1[02])[\\/\\-. ]?(31))|(19(?:[0][48]|[2468][048]|[13579][26])|20(?:[02468][048]|[13579][26]))[\\/\\-. ]?(02)[\\/\\-. ]?(29)) ((00|[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9]):([0-9]|[0-5][0-9]))$”.

boolean SzervizSzerelesKezdesIdoValidalas(): Boolean típusú metódus, ami a „txtSzerelesKezdIdo” nevű szöveges mező értékét ellenőrzi a következő minta alapján: „^(?:((?:19|20)[0-9]{2})[\\/\\-. ]?(?:(0[1-9]|1[0-2])[\\/\\-. ]?([0-2][1-8]|[12]0|09|19)|(0[13-9]|1[0-2])[\\/\\-. ]?(29|30)|(0[13578]|1[02])[\\/\\-. ]?(31))|(19(?:[0][48]|[2468][048]|[13579][26])|20(?:[02468][048]|[13579][26]))[\\/\\-. ]?(02)[\\/\\-. ]?(29))$”.

boolean SzervizSzerelesMunkaOraValidalas(): Boolean típusú metódus, ami a „txtSzerelesMOra” nevű szöveges mező értékét ellenőrzi a következő minta alapján: „^(\\d?[1-9]|[1-9]0)$”.

boolean JarmuHozzRendszamValidalas(): Boolean típusú metódus, ami a „txtRendszam” nevű szöveges mező értékét ellenőrzi a következő minta alapján: „^[A-Z]{3}\\-[\\d]{3}$”.

boolean regJelszoValidalas(): Boolean típusú metódus, ami a „txtRegJel-szo” nevű szöveges mező értékét ellenőrzi a következő minta alapján: „^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$”.
Természetesen nagyon sok validációs metódust használok a programba, de próbáltam kiemelni a legösszetettebeket, és validálás szempontjából különböző értékek szerint.

String jelszoTitkosita(String, String): A  metódusnak két bemenő pa-ramétere van: az első paraméter a megadott jelszót, melyet titkosít a metódus, a máso-dik paraméter pedig a salt. A metódus visszatérési értéke a tikosított jelszó.

void jarmuHozzaAdasaSzovegesMezokTorlese(): A megadott szöveges mezők értékét állítja üresre.

ArrayList<String> ugyfelAdatLista(): A lista arra szolgál, hogy az adott adattagokat átadva PDF-et generáljak. Ezenkívül öt hasonló metódust használok erre a célra, de mivel használhatóságukat és működésüket tekintve megegyeznek, nem fejtem ki részletesebben őket.

BufferedImage kepAtmeretez(BufferedImage, int, int): BufferedImage típusú metódus, melyet arra használok, hogy a kiválasztott képet felbontástól függetle-nül, az adott felbontásra redukálja a képet. A metódus három paraméteres, az első pa-raméter az eredeti kép, melynek a felbontását csökkentem, a második paraméter a kép szélessége, a harmadik paraméter a kép magassága. A metódus vissza térési értéke a csökkentett felbontású kép.

void kepBetolt(): Az „openFile” nevű változó segítségével a kiválasztott képet betölti a „bf” nevű változóba.

void kepMent(): A „saveFile” nevű változó segítségével a kiválasztott helyre menti el a „bf” nevű változóban tárolt adatot.

void loEroBeallit(): A „txtTeljes” nevű szöveges mező értékét váltja át, majd értékül adja a „lblLoero” nevű változónak.
5.	Tesztelés
5.1	Rendszerkövetelmények
A program futtatásához Microsoft Windows 10-es operációs szükséges, de mivel a Ja-va, egy platformfüggetlen nyelv, véleményem szerint más operációs rendszeren is fut-tatható, ezenkívül szükséges a Java futtatási környezet. A keresett járművek megtekin-téséhez szükséges egy webböngésző, az ajánlott a Google Chrome, hogy funkcionalitá-sában helyesen működjön minden. A szoftver futtatásához szükséges minimális hard-verkövetelmények a következőek:
•	legalább 1 GHz-es processzor
•	1 GB RAM
•	512 MB-s videókártya
•	minimum 1024x768-as felbontású monitor

5.2	Tesztelés menete
Program indítása adatbázis kapcsolat nélkül
TESZTESET	ELVÁRT EREDMÉNY	KAPOTT EREDMÉNY
Program elindulása	Hiba!( Vezérlő konstruktor nem tudott lefutni!) Com-muications link failure	Hibaüzenet, nem megfelelt.
Belépés gomb meg-nyomása	Hiba!(Beléphet metódus nem tudott lefutni!) Com-muications link failure	Hibaüzenet, nem megfelelt.


Ügyfél nevének tesztelése
BEVITT ÉR-TÉK	TESZTESET	ELVÁRT ERED-MÉNY	KAPOTT EREDMÉNY
üres mező	Felvitel gomb megnyomása	Hiba! Az ügyfél nevének formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
111	Felvitel gomb megnyomása	Hiba! Az ügyfél nevének formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
Kiss J3nő	Felvitel gomb megnyomása	Hiba! Az ügyfél nevének formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
„ ”	Felvitel gomb megnyomása	Hiba! Az ügyfél nevének formátuma nem megfelelő!	Nincs hibaüzenet, megfelelt
Nagy Béla	Felvitel gomb megnyomása	Az alkalmazás a következő szöveges mező tartalmát vizs-gálja.	Nincs hibaüzenet, megfelelt

Ügyfél elérhetőség tesztelése
BEVITT ÉR-TÉK	TESZTESET	ELVÁRT ERED-MÉNY	KAPOTT EREDMÉNY
üres mező	Felvitel gomb megnyomása	Hiba! Az ügyfél elérhetőségének formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
„ ”	Felvitel gomb megnyomása	Hiba! Az ügyfél elérhetőségének formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
telefonszám	Felvitel gomb megnyomása	Hiba! Az ügyfél elérhetőségének formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
+36201234567	Felvitel gomb megnyomása	Az alkalmazás a következő szöveges mező tartalmát vizs-gálja.	Nincs hibaüzenet, megfelelt
0611234567/369	Felvitel gomb megnyomása	Az alkalmazás a következő szöveges mező tartalmát vizs-gálja.	Nincs hibaüzenet, megfelelt

Autó rendszámának tesztelése
BEVITT ÉR-TÉK	TESZTESET	ELVÁRT ERED-MÉNY	KAPOTT EREDMÉNY
üres mező	Felvitel gomb megnyomása	Hiba! Az autó rend-szám formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
„ ”	Felvitel gomb megnyomása	Hiba! Az autó rend-szám formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
AAA 123	Felvitel gomb megnyomása	Hiba! Az autó rend-szám formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
abc-123	Felvitel gomb megnyomása	Hiba! Az autó rend-szám formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
ABC-123	Felvitel gomb megnyomása	Az alkalmazás a következő szöveges mező tartalmát vizs-gálja.	Nincs hibaüzenet, megfelelt

Munkalap számának tesztelése
BEVITT ÉR-TÉK	TESZTESET	ELVÁRT ERED-MÉNY	KAPOTT EREDMÉNY
üres mező	Felvitel gomb megnyomása	Hiba! A munkalap szám formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
„ ”	Felvitel gomb megnyomása	Hiba! A munkalap szám formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
munkalap 1234.	Felvitel gomb megnyomása	Hiba! A munkalap szám formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
um/2020/1234	Felvitel gomb megnyomása	Hiba! A munkalap szám formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
mu/2020/1234	Felvitel gomb megnyomása	Az alkalmazás a következő szöveges mező tartalmát vizs-gálja.	Nincs hibaüzenet, megfelelt

Munkalap vállalási határ idejének tesztelése
BEVITT ÉR-TÉK	TESZTESET	ELVÁRT ERED-MÉNY	KAPOTT EREDMÉNY
üres mező	Felvitel gomb megnyomása	Hiba! A munkalap vállalási határidő formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
„ ”	Felvitel gomb megnyomása	Hiba! A munkalap vállalási határidő formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
2020/03/30,17.00	Felvitel gomb megnyomása	Hiba! A munkalap vállalási határidő formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
dátum	Felvitel gomb megnyomása	Hiba! A munkalap vállalási határidő formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
2020-03-30	Felvitel gomb megnyomása	Hiba! A munkalap vállalási határidő formátuma nem megfelelő!	Hibaüzenet, nem megfelelt.
2020-03-30 19:00:00	Felvitel gomb megnyomása	Az alkalmazás a következő szöveges mező tartalmát vizs-gálja.	Nincs hibaüzenet, megfelelt

Feladat munkadíjának tesztelése
BEVITT ÉR-TÉK	TESZTESET	ELVÁRT ERED-MÉNY	KAPOTT EREDMÉNY
üres mező	Felvitel gomb megnyomása	Hiba! A feladat munka díjának for-mátuma nem megfe-lelő!	Hibaüzenet, nem megfelelt.
„ ”	Felvitel gomb megnyomása	Hiba! A feladat munka díjának for-mátuma nem megfe-lelő!	Hibaüzenet, nem megfelelt.
szám	Felvitel gomb megnyomása	Hiba! A feladat munka díjának for-mátuma nem megfe-lelő!	Hibaüzenet, nem megfelelt.
30000,0	Felvitel gomb megnyomása	Hiba! A feladat munka díjának for-mátuma nem megfe-lelő!	Hibaüzenet, nem megfelelt.
30000	Felvitel gomb megnyomása	Az alkalmazás a következő szöveges mező tartalmát vizs-gálja.	Nincs hibaüzenet, megfelelt

Felhasználó jelszó felvitel tesztelése
BEVITT ÉR-TÉK	TESZTESET	ELVÁRT ERED-MÉNY	KAPOTT EREDMÉNY
üres mező	Felvitel gomb megnyomása	Hiba! A jelszó for-mátuma nem megfe-lelő!	Hibaüzenet, nem megfelelt.
„ ”	Felvitel gomb megnyomása	Hiba! A jelszó for-mátuma nem megfe-lelő!	Hibaüzenet, nem megfelelt.
12345	Felvitel gomb megnyomása	Hiba! A jelszó for-mátuma nem megfe-lelő!	Hibaüzenet, nem megfelelt.
jelszo	Felvitel gomb megnyomása	Hiba! A jelszó for-mátuma nem megfe-lelő!	Hibaüzenet, nem megfelelt.
jelszo1	Felvitel gomb megnyomása	Hiba! A jelszó for-mátuma nem megfe-lelő!	Hibaüzenet, nem megfelelt.
jelszo11	Felvitel gomb megnyomása	Az alkalmazás a következő szöveges mező tartalmát vizs-gálja.	Nincs hibaüzenet, megfelelt

5.3	Teszteléskor felfedezett Hibák
A tesztelést követően felfedezett hibák leírása. Az szerviz menüpontban lévő munka-laphoz tartozó munkadíj, és anyagdíj felvitele nem jó, mivel az adatok felvitelénél nem az adott adatot tölti fel, hanem egy bedrótozott adatot.
Mindegyik táblázatnál, melyek adatokat jelenítenek meg az adatbázisból, miután el-kezdtem görgetni a táblázatott horizontális irányban, az oszlopok nem jellenek meg, beragad.
A ki buildelt JAR fájlt elindítva, nem az előre beállított felbontásban jellenek meg a komponensek a képernyőn.
6.	Továbbfejlesztési lehetőségek
Továbbfejlesztési lehetőségeket én két részre bontanám. Az első rész a meglévő funk-ciók bővítése (karbantartás), a másik rész új funkciók bevezetése (továbbfejlesztés).
6.1	Meglévő funkciók
Meglévő funkciókat több szempontból is tovább lehetne fejleszteni néhány példa: Az alkalmazás, és az adatbázis optimalizálása, biztonsági problémák kezelésének tovább-fejlesztése, felhasználó barátabb felület kialakítása. Az adatbázis táblái közt szerepel a kedvezmény tábla. Jelen pillanatban nem foglalkozom kedvezményekkel, melyek az alkalmazás szerviz részéhez kötődnek, de továbbfejlesztési szempontból az adatbázis-ban hagyom. A jármű keresés megvalósítása, az adott feltételek szerint.
6.2	Új funkciók
Egyéb járművek kezelését is lehessen kezelni az alkalmazással, mint például, kisha-szonjárművek, munkagépek, autóbuszok, lakókocsik, hajók, és alkatrészek.
Ezenkívül az alkalmazást olyan módon is tovább lehetne fejleszteni, hogy a felhaszná-ló a bejelentkezés pillanatában ki tudná választani a program nyelvét az általa válasz-tott nyelvre. Az alkalmazást webre is ki lehetne vezetni, olyan módon, hogy az ügyfe-lek tudjanak keresni az adott feltételekre szűrve a járművek között.
7.	Felhasználói dokumentáció
7.1	Rendszer követelmények
A program futtatásához Microsoft Windows 10-es operációs szükséges, de mivel a Ja-va, platformfüggetlen nyelv, véleményem szerint más operációs rendszeren is futtatha-tó, ahol rendelkezésre áll a Java futtatási környezet. A szoftver futtatásához szükséges minimális hardverkövetelmények a következőek:
•	legalább 1 GHz-es processzor
•	1 GB RAM
•	512 MB-s videókártya
•	minimum 1024x768-as felbontású monitor
7.2	Telepítés és futtatás
A program nem igényel külön telepítést, egyetlen futtatható állományt tartalmaz, a „JarmukezeloSzoftver.jar”-t. A program a „JarmukezeloSzoftver.jar” nevű fájlra kat-tintva indítható el.
7.3	Program leírása
Az alkalmazás egy autókereskedés járműveit tartja számon, ezen felül az ügyfelek autóvak kapcsolatos szervizeléseit látja el. Három fajta járművet lehet kezelni a prog-ramban, ezek a következőek:
•	személyautó
•	motorkerékpár
•	haszonjármű
Az alkalmazást az autó kereskedők, vagy szervizzel rendelkező tulajdonosok használ-hatják bármikor mikor szükségük van a járművek kezelésére, vagy esetleg egy ügyfél-nek szeretnék megmutatni a készleten lévő járműveket.
7.4	Menürendszer
A programban az alábbi menüpontok találhatóak. Mindegyik menüpontban miután a felhasználó megadta az adatokat, egy felugró ablak ugrik fel, mely jelzi, hogy az ada-tok feltöltése sikeres volt. Azonban mikor valamilyen módon, rosszul adott meg a fel-használó egy bizonyos adatott, értesítés kap erről, és a program addig nem engedi to-vább a felhasználót, amig nincs helyesen kitöltve az adott mező.

7.4.1	Belépés/Kilépés
Az alkalmazás ezzel a felülettel indul el. Mikor a felhasználó sikeresen megadta a he-lyes felhasználónév és jelszó kombinációt akkor a program a főmenübe irányítja át. Ha a felhasználó ki szeretne lépni a programból azt a főmenüben lévő kilépés gombbal teheti meg, illetve, ha bármelyik menüben bezárja az alkalmazást, a program ki fogja léptetni.

7.4.2	Főmenü
A főmenüből lehet elnavigálni az alkalmazásban található többi menüpontba.

7.4.3	Szerviz
Ebben a menüpontban az ügyfelek, márkák, autók, munkalapok, feladatok, szerelések, alkatrészek, és beszerzéseket adatait lehet feltölteni az adatbázisba, és a jelenleg mag-adott adatokat PDF-be lehet exportálni a beszerzés fülön található „munkalap készíté-se” nevű gombra kattintva. Ebben a menüben logikus sorrendben vannak megadva az almenük, ennek tudatában kell végig menni, balról jobbra.

A szerviz menü első ablakában az ügyfelek nevét, lakcímét, elérhetőségét, okmány típusát, és azonosítóját lehet megadni.

A második ablakban a márkákat lehet megadni, a hozzájuk tartozó márka kóddal, és elnevezéssel.



Az autók almenüpontban, az autó rendszámát, a hozzá tartozó ügyfelet, az autó márká-ját és típusát, a gyártási évét, színét, motorszámát, és alvázszámát lehet megadni.


A negyedik fülön a munkalapot lehet létrehozni, mégpedig úgy, hogy a munkalapszám megadása után, megadjuk az adott autó rendszámát, a vállalási időt, hogy mégis mikor lesz kész az adott autó, és a hibákat, amik leírják, hogy mit kell csinálni az autón.



A feladat almenüpontban lehet megadni, a feladatokat, amiket az autókon kell csinálni. Minden feladatnak van egyedi feladat azonosítója, munkadíja, típusa és elnevezése. Négy fajta típusú feladat van:
•	javítás
•	alkatrész csere
•	felkészítés vizsgára
•	vizsgáztatás


A szerelés almenüpontban a megadott munkalapszámhoz lehet szerelési feladatokat adni, az adott feladat számmal, és szerelővel. Minden szerelésnek van kezdeti, és befe-jezési ideje, és a szereléssel eltöltött munkaórák száma.

Az alkatrész menüpontban új alkatrészek megadására van lehetőség. Minden alkat-résznek van saját alkatrész kódja és megnevezése.



Az utolsó almenüpontban az adott munkalaphoz lehet hozzárendelni, egyedi beszerzési kóddal az alkatrészeket, és az árukat. Minden alkatrészhez ki kell tölteni az megrende-lési idejét.

7.4.4	Szerviz módosítása
A menüpontban az ügyfél adatait, és a beszerzés adatai lehet módosítani a 34.-ábrától a 35.- ábráig látható módon.




7.4.5	Jármű keresése
Ebben a menüpontban az adatbázisban tárolt járművek adatai lehet megtekinteni táb-lázat formájában.

7.4.6	Jármű hozzáadása
Járműveket lehet hozzá adni a 34.-ábrától a 35.-ábráig látható módon. Mindenképpen ki kell választani a feltöltendő jármű típusát, ezek után lehet megadni a jármű adatait.
