package com.cg.fsd4.kanban_board.exception;

public class ProjectNotfoundException  extends RuntimeException{
	private static final long serialVersionUID = 1L;

	static String msg;

	/**
	 * Instantiates a new resource not found.
	 *
	 * @param message the message
	 */
	public ProjectNotfoundException(String message) {
		this.msg = message;
	}
}



