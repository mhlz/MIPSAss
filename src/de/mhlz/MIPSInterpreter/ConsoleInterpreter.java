package de.mhlz.MIPSInterpreter;

import java.io.*;

/**
 * Created by mischa on 13.02.14.
 */
public class ConsoleInterpreter {



	public static void main(String[] args) throws IOException {
		boolean interactive = false;
		int sleepTime = 0;
		boolean silent = true;
		String filename = "";

		if(args.length < 1) {
			System.err.println("I need a file to interpret!");
			return;
		}

		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("-i")) {
				interactive = true;
			} else if(args[i].equals("-w")) {
				try {
					sleepTime = Integer.valueOf(args[i + 1]);
				} catch (IndexOutOfBoundsException e) {
					System.err.println("Incorrect argument!");
					return;
				}
				i++;
			} else if(args[i].equals("-v")) {
				silent = false;
			} else {
				filename = args[i];
			}
		}
		FileReader reader = new FileReader(new File(filename));
		BufferedReader fi = new BufferedReader(reader);
		String codeLine;
		String code = "";
		while((codeLine = fi.readLine()) != null) {
			code += codeLine + "\n";
		}
		fi.close();

		Interpreter i = new Interpreter();

		i.setCodeString(code);

		int spaces = 0;
		int counter = 0;
		while(i.tick() != -1) {
			if(sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if(counter > 0) {
				counter--;
				if(counter == 0) {
					interactive = true;
				}
			}

			if(!silent && !interactive) {
				for(int j = 0; j < spaces; j++) {
					System.out.print(" ");
				}
				System.out.print("\r" + i.getCurrentCodeLine() + "\r");
				spaces = i.getCurrentCodeLine().length();
			}

			if(interactive) {
				System.out.println(i);
				while(true) {
					System.out.print("Next line: " + i.getCurrentCodeLine() + "\n");
					System.out.print(">");
					BufferedReader cmd = new BufferedReader(new InputStreamReader(System.in));
					String command = cmd.readLine();
					if(command.startsWith("run")) {
						String[] params = command.split(" ");
						if(params.length == 1) {
							break;
						} else if(params.length == 2) {
							try {
								counter = Integer.parseInt(params[1]);
							} catch (NumberFormatException e) {
								counter = 0;
								if(params[1].equals("all")) {
									interactive = false;
									break;
								} else {
									continue;

								}
							}
							break;
						}
					} else if(command.equals("")) {
						break;
					}
				}
			}
		}

		if(!interactive) {
			System.out.println(i);
		}
	}
}
