# dsp-java-diagnosticToHealthCheck
Le projet diagnosticToHealthCheck permet d'intérpreter les différents 
tests santé des services en production.
- Pour permettre la conversion du fichier XCEL en tableau JSON, j'utilise le lien suivant
https://cloudmersive.com/convert-xlsx-to-json-tool
- Afin d'analyser les différents types de réponses des tests, j'ai utilisé des différentes techniques de comparaison des retours des réponses :
   * REGEX
   * FINDSTRING
   * HTTPCODE 