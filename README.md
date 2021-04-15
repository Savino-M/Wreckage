# WRECKAGE

La struttura della repository si presenta nel seguente modo:
```
|–– doc
|    |–– drawings
|    |–– Report.md
|–– res
|    |--db
|    |--sounds
|    |--saves
|–– src
|    |–– main
|    |–– test
|--target
|    |--classes
|    |-test-classes
|--pom.xml
|--wreckage.jar
|--wreckage.iml
|–– README.md
```

Nel seguito si dettagliano i ruoli dei diversi componenti:
- **doc**: in questa cartella si trova tutta la documentazione relativa al progetto. In particolare, in *drawings* sono salvati i diagrammi UML. Il file *Report.md* rappresenta la relazione del progetto;
- **res**: la cartella contiene tutte le risorse usate dal sistema(suoni,file,ecc). In sounds ci sono i file audio, in db c'è il database mentre in saves i file di salvataggio;
- **src**: la cartella principale del progetto, in cui si trova tutto il codice dell’applicazione. In *main* ci sono i file sorgente e *test* contiene i test di unità previsti,
- **pom.xml**: file di configurazione per Maven;
- **wreckage.jar**: eseguibile del gioco.
