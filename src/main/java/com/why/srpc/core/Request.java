/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.why.srpc.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

@SuppressWarnings({ "cast", "rawtypes", "serial", "unchecked" })
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-03-11")
public class Request implements org.apache.thrift.TBase<Request, Request._Fields>, java.io.Serializable, Cloneable,
		Comparable<Request> {
	private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct(
			"Request");

	private static final org.apache.thrift.protocol.TField UUID_FIELD_DESC = new org.apache.thrift.protocol.TField(
			"uuid", org.apache.thrift.protocol.TType.STRING, (short) 1);
	private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField(
			"name", org.apache.thrift.protocol.TType.STRING, (short) 2);
	private static final org.apache.thrift.protocol.TField URI_FIELD_DESC = new org.apache.thrift.protocol.TField("uri",
			org.apache.thrift.protocol.TType.STRING, (short) 3);
	private static final org.apache.thrift.protocol.TField PARAM_FIELD_DESC = new org.apache.thrift.protocol.TField(
			"param", org.apache.thrift.protocol.TType.MAP, (short) 4);

	private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
	static {
		schemes.put(StandardScheme.class, new RequestStandardSchemeFactory());
		schemes.put(TupleScheme.class, new RequestTupleSchemeFactory());
	}

	public String uuid; // required
	public String name; // required
	public String uri; // required
	public Map<String, String> param; // required

	/**
	 * The set of fields this struct contains, along with convenience methods
	 * for finding and manipulating them.
	 */
	public enum _Fields implements org.apache.thrift.TFieldIdEnum {
		UUID((short) 1, "uuid"), NAME((short) 2, "name"), URI((short) 3, "uri"), PARAM((short) 4, "param");

		private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

		static {
			for (_Fields field : EnumSet.allOf(_Fields.class)) {
				byName.put(field.getFieldName(), field);
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, or null if its not
		 * found.
		 */
		public static _Fields findByThriftId(int fieldId) {
			switch (fieldId) {
			case 1: // UUID
				return UUID;
			case 2: // NAME
				return NAME;
			case 3: // URI
				return URI;
			case 4: // PARAM
				return PARAM;
			default:
				return null;
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, throwing an exception
		 * if it is not found.
		 */
		public static _Fields findByThriftIdOrThrow(int fieldId) {
			_Fields fields = findByThriftId(fieldId);
			if (fields == null)
				throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
			return fields;
		}

		/**
		 * Find the _Fields constant that matches name, or null if its not
		 * found.
		 */
		public static _Fields findByName(String name) {
			return byName.get(name);
		}

		private final short _thriftId;
		private final String _fieldName;

		_Fields(short thriftId, String fieldName) {
			_thriftId = thriftId;
			_fieldName = fieldName;
		}

		public short getThriftFieldId() {
			return _thriftId;
		}

		public String getFieldName() {
			return _fieldName;
		}
	}

	// isset id assignments
	public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
	static {
		Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(
				_Fields.class);
		tmpMap.put(_Fields.UUID,
				new org.apache.thrift.meta_data.FieldMetaData("uuid", org.apache.thrift.TFieldRequirementType.REQUIRED,
						new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
		tmpMap.put(_Fields.NAME,
				new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.REQUIRED,
						new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
		tmpMap.put(_Fields.URI,
				new org.apache.thrift.meta_data.FieldMetaData("uri", org.apache.thrift.TFieldRequirementType.REQUIRED,
						new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
		tmpMap.put(_Fields.PARAM, new org.apache.thrift.meta_data.FieldMetaData("param",
				org.apache.thrift.TFieldRequirementType.REQUIRED,
				new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP,
						new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING),
						new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
		metaDataMap = Collections.unmodifiableMap(tmpMap);
		org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Request.class, metaDataMap);
	}

	public Request() {
	}

	public Request(String uuid, String name, String uri, Map<String, String> param) {
		this();
		this.uuid = uuid;
		this.name = name;
		this.uri = uri;
		this.param = param;
	}

	/**
	 * Performs a deep copy on <i>other</i>.
	 */
	public Request(Request other) {
		if (other.isSetUuid()) {
			this.uuid = other.uuid;
		}
		if (other.isSetName()) {
			this.name = other.name;
		}
		if (other.isSetUri()) {
			this.uri = other.uri;
		}
		if (other.isSetParam()) {
			Map<String, String> __this__param = new HashMap<String, String>(other.param);
			this.param = __this__param;
		}
	}

	public Request deepCopy() {
		return new Request(this);
	}

	public void clear() {
		this.uuid = null;
		this.name = null;
		this.uri = null;
		this.param = null;
	}

	public String getUuid() {
		return this.uuid;
	}

	public Request setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public void unsetUuid() {
		this.uuid = null;
	}

	/**
	 * Returns true if field uuid is set (has been assigned a value) and false
	 * otherwise
	 */
	public boolean isSetUuid() {
		return this.uuid != null;
	}

	public void setUuidIsSet(boolean value) {
		if (!value) {
			this.uuid = null;
		}
	}

	public String getName() {
		return this.name;
	}

	public Request setName(String name) {
		this.name = name;
		return this;
	}

	public void unsetName() {
		this.name = null;
	}

	/**
	 * Returns true if field name is set (has been assigned a value) and false
	 * otherwise
	 */
	public boolean isSetName() {
		return this.name != null;
	}

	public void setNameIsSet(boolean value) {
		if (!value) {
			this.name = null;
		}
	}

	public String getUri() {
		return this.uri;
	}

	public Request setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public void unsetUri() {
		this.uri = null;
	}

	/**
	 * Returns true if field uri is set (has been assigned a value) and false
	 * otherwise
	 */
	public boolean isSetUri() {
		return this.uri != null;
	}

	public void setUriIsSet(boolean value) {
		if (!value) {
			this.uri = null;
		}
	}

	public int getParamSize() {
		return (this.param == null) ? 0 : this.param.size();
	}

	public void putToParam(String key, String val) {
		if (this.param == null) {
			this.param = new HashMap<String, String>();
		}
		this.param.put(key, val);
	}

	public Map<String, String> getParam() {
		return this.param;
	}

	public Request setParam(Map<String, String> param) {
		this.param = param;
		return this;
	}

	public void unsetParam() {
		this.param = null;
	}

	/**
	 * Returns true if field param is set (has been assigned a value) and false
	 * otherwise
	 */
	public boolean isSetParam() {
		return this.param != null;
	}

	public void setParamIsSet(boolean value) {
		if (!value) {
			this.param = null;
		}
	}

	public void setFieldValue(_Fields field, Object value) {
		switch (field) {
		case UUID:
			if (value == null) {
				unsetUuid();
			} else {
				setUuid((String) value);
			}
			break;

		case NAME:
			if (value == null) {
				unsetName();
			} else {
				setName((String) value);
			}
			break;

		case URI:
			if (value == null) {
				unsetUri();
			} else {
				setUri((String) value);
			}
			break;

		case PARAM:
			if (value == null) {
				unsetParam();
			} else {
				setParam((Map<String, String>) value);
			}
			break;

		}
	}

	public Object getFieldValue(_Fields field) {
		switch (field) {
		case UUID:
			return getUuid();

		case NAME:
			return getName();

		case URI:
			return getUri();

		case PARAM:
			return getParam();

		}
		throw new IllegalStateException();
	}

	/**
	 * Returns true if field corresponding to fieldID is set (has been assigned
	 * a value) and false otherwise
	 */
	public boolean isSet(_Fields field) {
		if (field == null) {
			throw new IllegalArgumentException();
		}

		switch (field) {
		case UUID:
			return isSetUuid();
		case NAME:
			return isSetName();
		case URI:
			return isSetUri();
		case PARAM:
			return isSetParam();
		}
		throw new IllegalStateException();
	}

	@Override
	public boolean equals(Object that) {
		if (that == null)
			return false;
		if (that instanceof Request)
			return this.equals((Request) that);
		return false;
	}

	public boolean equals(Request that) {
		if (that == null)
			return false;

		boolean this_present_uuid = true && this.isSetUuid();
		boolean that_present_uuid = true && that.isSetUuid();
		if (this_present_uuid || that_present_uuid) {
			if (!(this_present_uuid && that_present_uuid))
				return false;
			if (!this.uuid.equals(that.uuid))
				return false;
		}

		boolean this_present_name = true && this.isSetName();
		boolean that_present_name = true && that.isSetName();
		if (this_present_name || that_present_name) {
			if (!(this_present_name && that_present_name))
				return false;
			if (!this.name.equals(that.name))
				return false;
		}

		boolean this_present_uri = true && this.isSetUri();
		boolean that_present_uri = true && that.isSetUri();
		if (this_present_uri || that_present_uri) {
			if (!(this_present_uri && that_present_uri))
				return false;
			if (!this.uri.equals(that.uri))
				return false;
		}

		boolean this_present_param = true && this.isSetParam();
		boolean that_present_param = true && that.isSetParam();
		if (this_present_param || that_present_param) {
			if (!(this_present_param && that_present_param))
				return false;
			if (!this.param.equals(that.param))
				return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		List<Object> list = new ArrayList<Object>();

		boolean present_uuid = true && (isSetUuid());
		list.add(present_uuid);
		if (present_uuid)
			list.add(uuid);

		boolean present_name = true && (isSetName());
		list.add(present_name);
		if (present_name)
			list.add(name);

		boolean present_uri = true && (isSetUri());
		list.add(present_uri);
		if (present_uri)
			list.add(uri);

		boolean present_param = true && (isSetParam());
		list.add(present_param);
		if (present_param)
			list.add(param);

		return list.hashCode();
	}

	public int compareTo(Request other) {
		if (!getClass().equals(other.getClass())) {
			return getClass().getName().compareTo(other.getClass().getName());
		}

		int lastComparison = 0;

		lastComparison = Boolean.valueOf(isSetUuid()).compareTo(other.isSetUuid());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetUuid()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uuid, other.uuid);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetName()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetUri()).compareTo(other.isSetUri());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetUri()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uri, other.uri);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetParam()).compareTo(other.isSetParam());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetParam()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.param, other.param);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		return 0;
	}

	public _Fields fieldForId(int fieldId) {
		return _Fields.findByThriftId(fieldId);
	}

	public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
		schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
	}

	public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
		schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Request(");
		boolean first = true;

		sb.append("uuid:");
		if (this.uuid == null) {
			sb.append("null");
		} else {
			sb.append(this.uuid);
		}
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("name:");
		if (this.name == null) {
			sb.append("null");
		} else {
			sb.append(this.name);
		}
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("uri:");
		if (this.uri == null) {
			sb.append("null");
		} else {
			sb.append(this.uri);
		}
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("param:");
		if (this.param == null) {
			sb.append("null");
		} else {
			sb.append(this.param);
		}
		first = false;
		sb.append(")");
		return sb.toString();
	}

	public void validate() throws org.apache.thrift.TException {
		// check for required fields
		if (uuid == null) {
			throw new org.apache.thrift.protocol.TProtocolException(
					"Required field 'uuid' was not present! Struct: " + toString());
		}
		if (name == null) {
			throw new org.apache.thrift.protocol.TProtocolException(
					"Required field 'name' was not present! Struct: " + toString());
		}
		if (uri == null) {
			throw new org.apache.thrift.protocol.TProtocolException(
					"Required field 'uri' was not present! Struct: " + toString());
		}
		if (param == null) {
			throw new org.apache.thrift.protocol.TProtocolException(
					"Required field 'param' was not present! Struct: " + toString());
		}
		// check for sub-struct validity
	}

	private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
		try {
			write(new org.apache.thrift.protocol.TCompactProtocol(
					new org.apache.thrift.transport.TIOStreamTransport(out)));
		} catch (org.apache.thrift.TException te) {
			throw new java.io.IOException(te);
		}
	}

	private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
		try {
			read(new org.apache.thrift.protocol.TCompactProtocol(
					new org.apache.thrift.transport.TIOStreamTransport(in)));
		} catch (org.apache.thrift.TException te) {
			throw new java.io.IOException(te);
		}
	}

	private static class RequestStandardSchemeFactory implements SchemeFactory {
		public RequestStandardScheme getScheme() {
			return new RequestStandardScheme();
		}
	}

	private static class RequestStandardScheme extends StandardScheme<Request> {

		public void read(org.apache.thrift.protocol.TProtocol iprot, Request struct)
				throws org.apache.thrift.TException {
			org.apache.thrift.protocol.TField schemeField;
			iprot.readStructBegin();
			while (true) {
				schemeField = iprot.readFieldBegin();
				if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
					break;
				}
				switch (schemeField.id) {
				case 1: // UUID
					if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
						struct.uuid = iprot.readString();
						struct.setUuidIsSet(true);
					} else {
						org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
					}
					break;
				case 2: // NAME
					if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
						struct.name = iprot.readString();
						struct.setNameIsSet(true);
					} else {
						org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
					}
					break;
				case 3: // URI
					if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
						struct.uri = iprot.readString();
						struct.setUriIsSet(true);
					} else {
						org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
					}
					break;
				case 4: // PARAM
					if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
						{
							org.apache.thrift.protocol.TMap _map0 = iprot.readMapBegin();
							struct.param = new HashMap<String, String>(2 * _map0.size);
							String _key1;
							String _val2;
							for (int _i3 = 0; _i3 < _map0.size; ++_i3) {
								_key1 = iprot.readString();
								_val2 = iprot.readString();
								struct.param.put(_key1, _val2);
							}
							iprot.readMapEnd();
						}
						struct.setParamIsSet(true);
					} else {
						org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
					}
					break;
				default:
					org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
				}
				iprot.readFieldEnd();
			}
			iprot.readStructEnd();

			// check for required fields of primitive type, which can't be
			// checked in the validate method
			struct.validate();
		}

		public void write(org.apache.thrift.protocol.TProtocol oprot, Request struct)
				throws org.apache.thrift.TException {
			struct.validate();

			oprot.writeStructBegin(STRUCT_DESC);
			if (struct.uuid != null) {
				oprot.writeFieldBegin(UUID_FIELD_DESC);
				oprot.writeString(struct.uuid);
				oprot.writeFieldEnd();
			}
			if (struct.name != null) {
				oprot.writeFieldBegin(NAME_FIELD_DESC);
				oprot.writeString(struct.name);
				oprot.writeFieldEnd();
			}
			if (struct.uri != null) {
				oprot.writeFieldBegin(URI_FIELD_DESC);
				oprot.writeString(struct.uri);
				oprot.writeFieldEnd();
			}
			if (struct.param != null) {
				oprot.writeFieldBegin(PARAM_FIELD_DESC);
				{
					oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING,
							org.apache.thrift.protocol.TType.STRING, struct.param.size()));
					for (Map.Entry<String, String> _iter4 : struct.param.entrySet()) {
						oprot.writeString(_iter4.getKey());
						oprot.writeString(_iter4.getValue());
					}
					oprot.writeMapEnd();
				}
				oprot.writeFieldEnd();
			}
			oprot.writeFieldStop();
			oprot.writeStructEnd();
		}

	}

	private static class RequestTupleSchemeFactory implements SchemeFactory {
		public RequestTupleScheme getScheme() {
			return new RequestTupleScheme();
		}
	}

	private static class RequestTupleScheme extends TupleScheme<Request> {

		public void write(org.apache.thrift.protocol.TProtocol prot, Request struct)
				throws org.apache.thrift.TException {
			TTupleProtocol oprot = (TTupleProtocol) prot;
			oprot.writeString(struct.uuid);
			oprot.writeString(struct.name);
			oprot.writeString(struct.uri);
			{
				oprot.writeI32(struct.param.size());
				for (Map.Entry<String, String> _iter5 : struct.param.entrySet()) {
					oprot.writeString(_iter5.getKey());
					oprot.writeString(_iter5.getValue());
				}
			}
		}

		public void read(org.apache.thrift.protocol.TProtocol prot, Request struct)
				throws org.apache.thrift.TException {
			TTupleProtocol iprot = (TTupleProtocol) prot;
			struct.uuid = iprot.readString();
			struct.setUuidIsSet(true);
			struct.name = iprot.readString();
			struct.setNameIsSet(true);
			struct.uri = iprot.readString();
			struct.setUriIsSet(true);
			{
				org.apache.thrift.protocol.TMap _map6 = new org.apache.thrift.protocol.TMap(
						org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING,
						iprot.readI32());
				struct.param = new HashMap<String, String>(2 * _map6.size);
				String _key7;
				String _val8;
				for (int _i9 = 0; _i9 < _map6.size; ++_i9) {
					_key7 = iprot.readString();
					_val8 = iprot.readString();
					struct.param.put(_key7, _val8);
				}
			}
			struct.setParamIsSet(true);
		}
	}

}
