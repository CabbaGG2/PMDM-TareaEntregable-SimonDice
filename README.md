```mermaid
stateDiagram-v2

    Idle --> Generando
    Generando -->Adivinando
    Adivinando --> Adivinando 
    Adivinando --> Record/Puntuacion
    Record/Puntuacion -->Idle



  
