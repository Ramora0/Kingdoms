JSONSerializable:
Allows the class to be serialized into JSON and created from JSON
Requires the class to have a toJSON and fromJSON method. 
It also includes a static createFromJSON method which is used to create an instance of the class from a JSON object.
createFromJSON takes a JSON object and the class you want to create an instance of
If you don't know what instance subclass you want to create, you can use the static implementation of createFromJSON from the class.
i.e. Tile.createFromJSON(json) infers the subclass based on the attributes of the JSON object

JSONReferenceSerializable:
Allows a class to be serialized into a reference JSON and found given a JSON object
Requires a toReferenceJSON method, which tells the code where to find a certain object
Requires a static fromReferenceJSON method (via JSONEnforcer) which finds the object JSON is referring to