package br.com.ostech.enuns;

public enum OrderStatus {

    OPEN {
        public OrderStatus statusNext() {
            return FINISHED;
        }
    },
    REOPENED{
        public OrderStatus statusNext(){
            return FINISHED;
        }
    },
    FINISHED{
        public OrderStatus statusNext(){
            return REOPENED;
        }
    }
}
