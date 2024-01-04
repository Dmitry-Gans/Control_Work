package encapsulation.lib;

public class Console {
    public static final Console out = new Console();
    private Conf conf = null;
    private Conf confBcp = null;

    static class Conf {
        private int color;
        private int style;
        private boolean bg;
        private boolean light;
        private boolean reset;

        public Conf(Conf conf) {
            this(conf.color, conf.style, conf.bg, conf.light, conf.reset);
        }

        public Conf(int color, int style, boolean bg, boolean light, boolean reset) {
            this.color = color;
            this.style = style;
            this.bg = bg;
            this.light = light;
            this.reset = reset;
        }
    }

    public enum Color {
        RESET(0), BLACK(30), RED(31), GREEN(32), YELLOW(33), BLUE(34), PURPLE(35), CYAN(36), WHITE(37);
        final int data;

        Color(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return this.data == 0 ? "\033[0m" : String.format("\033[0;%dm", this.data);
        }
    }

    public enum Style {
        NORMAL(0), BOLD(1), ITALIC(3), UNDERLINE(4), INVERSE(7), CROSS(9);
        final int data;

        Style(int data) {
            this.data = data;
        }
    }

    public Console() {
        this.init();
    }

    /** Settings */
    public Console color(Color value) {
        this.conf.color = value.data;
        return this;
    }

    public Console color(String value) {
        this.conf.color = switch (value) {
            case "n", "black" -> Color.BLACK.data;
            case "r", "red" -> Color.RED.data;
            case "g", "green" -> Color.GREEN.data;
            case "y", "yellow" -> Color.YELLOW.data;
            case "b", "blue" -> Color.BLUE.data;
            case "p", "purple" -> Color.PURPLE.data;
            case "c", "cyan" -> Color.CYAN.data;
            case "w", "white" -> Color.WHITE.data;
            default -> Color.RESET.data;
        };
        return this;
    }

    public Console style(Style value) {
        this.conf.style = value.data;
        return this;
    }

    public Console style(String value) {
        this.conf.style = switch (value) {
            case "b", "bold" -> Style.BOLD.data;
            case "i", "italic" -> Style.ITALIC.data;
            case "u", "underline" -> Style.UNDERLINE.data;
            case "v", "inverse" -> Style.INVERSE.data;
            case "d", "cross" -> Style.CROSS.data;
            default -> Style.NORMAL.data;
        };
        return this;
    }

    public Console bg(boolean value) {
        this.conf.bg = value;
        return this;
    }

    public Console light(boolean value) {
        this.conf.light = value;
        return this;
    }

    public Console reset(boolean value) {
        this.conf.reset = value;
        return this;
    }

    /** View */
    public void log(String format, Object... params) {
        System.out.printf(this.str(format), params);
        if (this.conf.reset) this.init();
    }

    public void log(Object obj) {
        this.log("%s\n", obj);
    }

    public void err(String format, Object... params) {
        this.init().color(Color.RED);
        System.out.printf(this.str(format), params);
        this.initBcp();
    }

    public void err(Object obj) {
        this.err("--- Error ---\n%s\n-------------\n", obj);
    }

    /** Private */
    private Console init() {
        return this.init(new Conf(0, 0, false, false, true));
    }

    private Console init(Conf conf) {
        this.confBcp = this.conf != null ? new Conf(this.conf) : new Conf(conf);
        this.conf = new Conf(conf);
        return this;
    }

    public Console initBcp() {
        if (this.confBcp != null) this.conf = new Conf(this.confBcp);
        return this;
    }

    private String str(String str) {
        String color = "\033[0m";
        if (this.conf.color != 0) {
            int value = this.conf.color;
            if (this.conf.bg) value += 10;
            if (this.conf.light) value += 60;
            color = String.format("\033[%d;%dm", this.conf.style, value);
        }
        return color + str + "\033[0m";
    }
}