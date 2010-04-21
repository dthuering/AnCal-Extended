package pl.magot.vetch.ancal;

import java.util.NoSuchElementException;

import pl.magot.vetch.ancal.R;

/**
 * Represents type of a message shown in a messagebox.
 */
public enum MessageType {
	INFO(1, R.string.msgTypeInfo, R.drawable.msgicon_info),
	WARNING(2, R.string.msgTypeWarning, R.drawable.msgicon_warning),
	ERROR(3, R.string.msgTypeError, R.drawable.msgicon_error);
	
	
	public static MessageType create(int id) {
		for (MessageType type : values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		
		throw new NoSuchElementException("unknown message type (id = " + id + ")!");
	}
	
	private final int typeId;
	private final int titleId;
	private final int iconId;
	
	private MessageType(int id, int title, int icon) {
		typeId = id;
		titleId = title;
		iconId = icon;
	}
	
	public int getId() {
		return typeId;
	}
	
	public int getTitle() {
		return titleId;
	}
	
	public int getIcon() {
		return iconId;
	}
}
