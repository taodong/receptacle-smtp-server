package com.wedgeup.mail.receptacle.smtp.command;


import com.wedgeup.mail.receptacle.smtp.Session;

import java.io.IOException;

public class HelloCommand extends BaseCommand {
	public HelloCommand() {
		super(CommandVerb.HELO, "Introduce yourself.", "<hostname>");
	}

	@Override
	public void execute(final String commandString, final Session sess) throws IOException {
		final String[] args = this.getArgs(commandString);
		if (args.length < 2) {
			sess.sendResponse("501 Syntax: HELO <hostname>");
			return;
		}

		sess.resetMailTransaction();
		sess.setHelo(args[1]);

		sess.sendResponse("250 " + sess.getServer().getHostName());
	}
}
