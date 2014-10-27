package com.innovez.learn.greet;

/**
 * Simple dto object carrying greeting message content.
 * 
 * @author zakyalvan
 */
public class Greeting {
	private String content;
	
	public Greeting(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Greeting other = (Greeting) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}
}
