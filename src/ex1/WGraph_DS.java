package ex1;

public class WGraph_DS {







    private class Node implements node_info {
        private int key;
        private String info;
        private int tag;
        /**
         * Return the key (id) associated with this node.
         * @return key
         */
        @Override
        public int getKey() {
            return this.key;
        }

        /**
         * the getter of info
         *
         * @return info
         */
        @Override
        public String getInfo() {
            return this.info;
        }

        /**
         * the setter of info
         *
         * @param s
         */
        @Override
        public void setInfo(String s) {
        this.info = s;
        }

        /**
         * the getter of tag
         * @return tag
         */
        @Override
        public double getTag() {
            return this.tag;
        }

        /** the setter of tag
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(double t) {
            this.tag = tag;
        }
    }
}

