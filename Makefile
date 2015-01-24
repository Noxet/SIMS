
.PHONY: all clean

all:
	javac common/*.java server/*.java client/*.java

clean:
	rm -f common/*.class server/*.class client/*.class
