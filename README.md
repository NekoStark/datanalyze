# Datanalyze
## Parser per l'analisi di testo e il calcolo di indici di readability

### Utilizzo
- Scaricare l'ultima versione del jar dalla [pagina di release](https://github.com/NekoStark/datanalyze/releases)
- Andare nella directory dove si trova il jar e lanciare il jar con le seguenti opzioni:

```shell
java -jar datanalyze
 -i,--inputFile <arg>          file di input, può essere un pdf o un txt
 -o,--outputDir <arg>          la directory dove vengono salvati gli output
                               (n.b. i file di output vengono sempre sovrascritti)
 
 opzionale:
 -d,--treatAsDiphthong <arg>   se messo a true (come default), tutte i gruppi 
                               di vocali vengono trattati come dittonghi.
                               Se invece viene messo a false, vengono trattati
                               tutti come iati.
```

- Nella directory di output vengono prodotti i seguenti file di output
  - ```extracted```: se l'input è un pdf, viene salvato il contenuto in txt
  - ```stats```: statistiche estratte dal testo (e.g. numero di parole)
  - ```indexes```: gli indici di leggibilità calcolati

### Funzionamento
Il funzionamento principale dell'applicazione si basa sulle seguenti classi: 
- ```TextAnalyzer```, che si occupa di estrarre dal testo in input le metriche necessarie per il calcolo degli indici. La classe applica prima una divisione in frasi, poi per parole, ripulendo da punteggiatura e altri caratteri estranei. Viene poi applicata una lista di stopwords per rimuovere parole non significative, e infine viene divisa la parola in sillabe. (n.b. le regole secondo cui vengono fatte queste operazioni sono descritte al momento nel file ```application.properties``` e non sono ancora configurabili dall'esterno). Il prodotto di questa operazione è un oggetto ```Document``` che contiene tutte le informazioni estratte, insieme ai valori originali
- ```IndexCalculator```, è un interfaccia che definisce un unico metodo ```execute(Document d)```. Questa interfaccia è implementata dalle classi che rappresentano il calcolo effettivo degli indici (e.g. ```ColemanLiauIndexCalculator```)

Altre classi degne di menzione:
- ```Syllabifier```, che implementa l'operazione di sillabazione. Essendo questa operazione dipendente da alcuni dati non ricavabili dal testo (principalmente, dove cadono gli accenti tonici in una parola), si fanno alcune assunzioni sui gruppi di vocali per trattarli in modo coerente e controllato. Questa opzione può essere cambiata usando il parametro ```-d``` quando si lancia il jar
- ```TextReader```, controlla che il file sia supportato, e poi estrae il testo restituendo una stringa
- ```ResultsWriter```, scrive i file nella cartella specificata dal parametro ```-o```. I file vengono sovrascritti ad ogni esecuzione.

### Indici di leggibilità implementati

- Automated Readablity
- Coleman Liau
- Flesch Reading Ease
- Flesch Kincaid
- Fog Index
- Lix Index
- SMOG Index

