# oop-lab08 - Teoria

Effettuare analisi statica significa utilizzare un software che analizza il codice sorgente per individuare:

* Potenziali bug
* Possibili miglioramenti
* Codice duplicato
* Stile non conforme



Fare ciò consente di migliorare la qualità del codice, in termini di scrittura univoca e in termini di uniformità di gruppo.



I software che effettuano code checking sono eseguibili in 2 modalità:

* Stand-alone: il software viene eseguito e genera un report
* Plug-in: il software viene integrato con l'IDE e segnala i problemi sotto forma di warning

Ad esempio, Gradle è un misto: è un plug-in configurato per eseguire il software nella modalità stand-alone.



Per fare code checking useremo:

* SpotBugs, che scansiona il bytecode generato dal compilatore per scoprire se ci sono potenziali bug nel sorgente
* PMD, che si occupa di trovare imperfezioni nel codice.
  Questo integra CPD, che verifica se ci sono blocchi di codice copia-incollati
* Checkstyle, che si occupa di trovare errori di stile



Ognuno di questi tool ha un proprio plug-in Gradle per essere attivato (ma noi usiamo quello preconfigurato del prof).



Nella realizzazione di un test bisogna tenere conto di:

* Sensibilità, cioè la capacità di identificare i casi positivi di un test
* Specificità, cioè la capacità di identificare i casi negativi di un test

Queste 2 caratteristiche devono essere ben bilanciate per evitare errori di "eccesso o difetto" (falsi positivi e falsi negativi).



Per gestire la maggior parte del falsi positivi si utilizza la soppressione: viene disabilitata l'analisi statica nel punto in cui è presenti il falso positivo.

* Su Checkstyle la soppressione avviene tramite commenti ed è configurabile
* Su PMD la soppressione avviene tramite commenti in linea
* Su Spotbugs la soppressione avviene con una annotazione: `@SuppressFBWarnings`
