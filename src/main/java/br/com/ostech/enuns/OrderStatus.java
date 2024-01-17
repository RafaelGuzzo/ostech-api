package br.com.ostech.enuns;

public enum OrderStatus {

    OPEN {
        public OrderStatus next() {
            return FINISHED;
        }
    },
    REOPENED {
        public OrderStatus next() {
            return FINISHED;
        }
    },
    FINISHED {
        public OrderStatus next() {
            return REOPENED;
        }
    };

    OrderStatus() {
    }

    public abstract OrderStatus next();
}
