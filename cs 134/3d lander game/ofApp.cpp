
//--------------------------------------------------------------
//
//  Kevin M. Smith
//
//  Octree Test - startup scene
// 
//
//  Student Name:   < Areeb Yaqub >
//  Date: <date of last version>


#include "ofApp.h"
#include "Util.h"


//--------------------------------------------------------------
// setup scene, lighting, state and load geometry
//
void ofApp::setup() {
	bWireframe = false;
	bDisplayPoints = false;
	bAltKeyDown = false;
	bCtrlKeyDown = false;
	bLanderLoaded = false;
	bTerrainSelected = true;
	//	ofSetWindowShape(1024, 768);
	cam.setDistance(30);
	cam.setNearClip(.1);
	cam.setFov(65.5);   // approx equivalent to 28mm in 35mm format
	ofSetVerticalSync(true);
	
	cam.disableMouseInput();
	ofEnableSmoothing();
	ofEnableDepthTest();
	//Background image
	bg.loadImage("images/dims.jpg");
	bg.resize(ofGetWidth(), ofGetHeight());

	// setup rudimentary lighting 
	//
	initLightingAndMaterials();

	//moon.loadModel("geo/moon-houdini.obj");
	moon.loadModel("geo/mars-low-5x-v2.obj");
	moon.setScaleNormalization(false);

	// create sliders for testing
	//
	gui.setup();
	gui.add(numLevels.setup("Number of Octree Levels", 7, 1, 10));
	bHide = false;

	//  Create Octree for testing.
	//
	//float before = ofGetElapsedTimeMillis();
	octree.create(moon.getMesh(0), 20);
	//float after = ofGetElapsedTimeMillis();

	//cout << "octree make: " << (after - before)  <<  " Millis"    << endl;

	cout << "Number of Verts: " << moon.getMesh(0).getNumVertices() << endl;

	//testBox = Box(Vector3(3, 3, 0), Vector3(5, 5, 2));

	if (lander.loadModel("geo/lander.obj")) {
		bLanderLoaded = true;
		lander.setScaleNormalization(false);
		lander.setPosition(0, 0, 0);
		cout << "number of meshes: " << lander.getNumMeshes() << endl;
		bboxList.clear();
		for (int i = 0; i < lander.getMeshCount(); i++) {
			bboxList.push_back(Octree::meshBounds(lander.getMesh(i)));
		}
		glm::vec3 min = lander.getSceneMin();
		glm::vec3 max = lander.getSceneMax();

		// set up bounding box for lander while we are at it
		//
		landerBounds = Box(Vector3(min.x, min.y, min.z), Vector3(max.x, max.y, max.z));
	}

	//emitter setup
	thrusterForce = new ThrusterForce(ofVec3f(0, -20, 0));
	gravityForce = new GravityForce(ofVec3f(0, -.4, 0));
	cyclicForce = new CyclicForce(10);
	turbForce = new TurbulenceForce(ofVec3f(-20, -20, -20), ofVec3f(20, 20, 20));
	radialForce = new ImpulseRadialForce(200.0);


	emitter.sys->addForce(turbForce);
	emitter.sys->addForce(thrusterForce);
	emitter.sys->addForce(gravityForce);
	//emitter.sys->addForce(cyclicForce);
	//emitter.sys->addForce(radialForce);



	emitter.setVelocity(ofVec3f(0, 0, 0));
	emitter.setEmitterType(RadialEmitter);
	emitter.setGroupSize(200);
	//ofSeedRandom();
	emitter.setLifespan(.5);
	emitter.setRate(0);
	emitter.setParticleRadius(.1);
	emitter.setRandomLife(true);
	emitter.sys->reset();
	emitter.start();


	//particle setup
	player.position = ofVec3f(0,0,0);
	lander.setPosition(player.position.x, player.position.y, player.position.z); 
	player.radius = .5;      // in 3D, you wil want to make this much smaller !
	player.lifespan = -1;
	sys.add(player);
	//sys.addForce(new GravityForce(ofVec3f(0, 10, 0)));


	//lander variables for integrator
	vel = ofVec3f(0, 0, 0);
	accel = ofVec3f(0, -2, 0);
	damping = .99;
	
	rot = lander.getRotationAngle(0);


	
}

//--------------------------------------------------------------
// incrementally update scene (animation)
//
void ofApp::update() {
	checkCollisions();

	float dt = 1 / 60.f;
	heading = ofVec3f(cosf(glm::radians(rot)), 0, sinf(glm::radians(rot)));
	sideh = ofVec3f(cosf(glm::radians(rot - 90)), 0, sinf(glm::radians(rot - 90)));

	if (forward) sys.particles[0].velocity += heading / 7;
	if (backward) sys.particles[0].velocity += -heading / 7;
	if (left)  sys.particles[0].velocity += sideh / 7;
	if (right) sys.particles[0].velocity += -sideh / 7;
	if (w) sys.particles[0].velocity += ofVec3f(0, .4, 0);
	if (a) rvel = -45;
	if (s) sys.particles[0].velocity += ofVec3f(0, -.4, 0);
	if (d) rvel = 45;

	//movement
	position = position + sys.particles[0].velocity * dt;
	player.position = position;
	lander.setPosition(position.x, position.y, position.z);
	vel = vel + accel * dt;
	vel = vel * damping;

	//rotation
	rot = rot + rvel * dt;
	lander.setRotation(0, -rot, 0, 1, 0);
	rvel = rvel + raccel * dt;
	rvel = rvel * damping;

	emitter.setPosition(ofVec3f(position.x, position.y + 1, position.z));

	ofVec3f min = lander.getSceneMin() + lander.getPosition();
	ofVec3f max = lander.getSceneMax() + lander.getPosition();

	Box bounds = Box(Vector3(min.x, min.y, min.z), Vector3(max.x, max.y, max.z));

	colBoxList.clear();
	octree.intersect(bounds, octree.root, colBoxList);

	if (bounds.overlap(testBox)) {
		cout << "overlap" << endl;
	}
	else {
		cout << "OK" << endl;
	}
	

	ofSeedRandom(); // helps set up the thruster emitter
	radialForce->setHeight(0); //attempt to clamp but doesn't seem to work. 
	emitter.update();
	sys.update();
	
}
//--------------------------------------------------------------
void ofApp::draw() {

	

	glDepthMask(false);
	if (!bHide) gui.draw();
	bg.draw(0,0,0);
	ofSetColor(ofColor::white);
	//altitude writing
	ofSetColor(ofColor::lightGreen);
	string altitude;
	ofVec3f q;
	altitude = "Altitude: " + std::to_string(findAlt(q));
	ofDrawBitmapString(altitude, ofPoint(10, 20));
	ofSetColor(ofColor::white);
	glDepthMask(true);
	
	cam.begin();
	
	ofPushMatrix();
	if (bWireframe) {                    // wireframe mode  (include axis)
		ofDisableLighting();
		ofSetColor(ofColor::slateGray);
		moon.drawWireframe();
		if (bLanderLoaded) {
			lander.drawWireframe();
			if (!bTerrainSelected) drawAxis(lander.getPosition());
		}
		if (bTerrainSelected) drawAxis(ofVec3f(0, 0, 0));
	}
	else {
		ofEnableLighting();              // shaded mode
		moon.drawFaces();
		ofMesh mesh;

		if (bLanderLoaded) {
			lander.drawFaces();
			if (!bTerrainSelected) drawAxis(lander.getPosition());
			if (bDisplayBBoxes) {
				ofNoFill();
				ofSetColor(ofColor::white);
				for (int i = 0; i < lander.getNumMeshes(); i++) {
					ofPushMatrix();
					ofMultMatrix(lander.getModelMatrix());
					ofRotate(-90, 1, 0, 0);
					Octree::drawBox(bboxList[i]);
					ofPopMatrix();
				}
			}
			if (bLanderSelected) {

				ofVec3f min = lander.getSceneMin() + lander.getPosition();
				ofVec3f max = lander.getSceneMax() + lander.getPosition();
				ofSetColor(ofColor::white);
				ofNoFill();
				Box bounds = Box(Vector3(min.x, min.y, min.z), Vector3(max.x, max.y, max.z));

				Octree::drawBox(bounds);

				// draw colliding boxes
				//
				ofSetColor(ofColor::darkGoldenRod);
				for (int i = 0; i < colBoxList.size(); i++) {
					Octree::drawBox(colBoxList[i]);
				}
			}
		}

	}
	//if statement checks to various cases Start
	if (bTerrainSelected) drawAxis(ofVec3f(0, 0, 0));

	if (bDisplayPoints) {                // display points as an option    
		glPointSize(3);
		ofSetColor(ofColor::green);
		moon.drawVertices();
	}

	// highlight selected point (draw sphere around selected point)
	//
	if (bPointSelected) {
		ofSetColor(ofColor::blue);
		ofDrawSphere(selectedPoint, .1);
	}

	// recursively draw octree
	//
	ofDisableLighting();
	int level = 0;
	//	ofNoFill();

	if (bDisplayLeafNodes) {
		octree.drawLeafNodes(octree.root);
		cout << "num leaf: " << octree.numLeaf << endl;
	}
	else if (bDisplayOctree) {
		ofNoFill();
		ofSetColor(ofColor::white);
		octree.draw(numLevels, 0);
	}

	// if point selected, draw a sphere
	//
	if (pointSelected) {
		ofVec3f p = octree.mesh.getVertex(selectedNode.points[0]);
		ofVec3f d = p - cam.getPosition();
		ofSetColor(ofColor::lightGreen);
		ofDrawSphere(p, .02 * d.length());
		ofSetColor(ofColor::white);
	}
	//if checks end



	//emitters and sys draws
	emitter.draw();
	sys.draw();


	ofPopMatrix();
	cam.end();

	
}


// 
// Draw an XYZ axis in RGB at world (0,0,0) for reference.
//
void ofApp::drawAxis(ofVec3f location) {

	ofPushMatrix();
	ofTranslate(location);

	ofSetLineWidth(1.0);

	// X Axis
	ofSetColor(ofColor(255, 0, 0));
	ofDrawLine(ofPoint(0, 0, 0), ofPoint(1, 0, 0));


	// Y Axis
	ofSetColor(ofColor(0, 255, 0));
	ofDrawLine(ofPoint(0, 0, 0), ofPoint(0, 1, 0));

	// Z Axis
	ofSetColor(ofColor(0, 0, 255));
	ofDrawLine(ofPoint(0, 0, 0), ofPoint(0, 0, 1));

	ofPopMatrix();
}

void ofApp::checkCollisions() {
	//thruster collisions
	for (int i = 0; i < emitter.sys->particles.size(); i++) {

		// only bother to check for descending particles.
		//
		ofVec3f velo = emitter.sys->particles[i].velocity; // velocity of particle
		if (velo.y >= 0) break;                             // ascending;

		ofVec3f pos = emitter.sys->particles[i].position;


		if (pos.y < emitter.sys->particles[i].radius) {

			// apply impulse function
			//
			ofVec3f norm = ofVec3f(0, 1, 0);  // just use vertical for normal for now
			ofVec3f f = (.10 + 1.0)*((-velo.dot(norm))*norm);
			emitter.sys->particles[i].forces += ofGetFrameRate() * f;
		}
	}

	//lander particle collisison check 
	ofVec3f v = sys.particles[0].velocity; // velocity of particle
							   // ascending;

	ofVec3f pos;// = player.position;


	//if (pos.y < player.radius) {
	float alt = findAlt(pos);

	if (alt < 0) {
		// apply impulse function
		//
		ofVec3f norm = ofVec3f(0, 1, 0);  // just use vertical for normal for now
		
		ofVec3f f = (.85 + 1.0)*((-v.dot(norm))*norm);
		
		sys.particles[0].forces += ofGetFrameRate() * f;
	}
	
	/*if (vel.y >= 0) return;  // ascending;

	ofVec3f pos;//= lander.getPosition();

	float alt = findAlt(pos);

	if (alt < 0) {

		// apply impulse function
		//
		ofVec3f norm = ofVec3f(0, 1, 0);  // just use vertical for normal for now
		ofVec3f f = (restitution + 1.0)*((-vel.dot(norm))*norm);
		vel += ofGetFrameRate() * f;
	}
	*/
}

void ofApp::keyPressed(int key) {

	switch (key) {
	case 'B':
	case 'b':
		bDisplayBBoxes = !bDisplayBBoxes;
		break;
	case 'C':
	case 'c':
		if (cam.getMouseInputEnabled()) cam.disableMouseInput();
		else cam.enableMouseInput();
		break;
	case 'F':
	case 'f':
		ofToggleFullscreen();
		break;
	case 'H':
	case 'h':
		break;
	case 'L':
	case 'l':
		bDisplayLeafNodes = !bDisplayLeafNodes;
		break;
	case 'O':
	case 'o':
		bDisplayOctree = !bDisplayOctree;
		break;
	case 'r':
		cam.reset();
		break;
	case 'p':
		savePicture();
		break;
	case 't': //previously s
		setCameraTarget();
		break;
	case 'u':
		break;
	case 'v':
		togglePointsDisplay();
		break;
	case 'V':
		break;
	case 'y': //previously w
		toggleWireframeMode();
		break;
	case 'w':
		w = true;
		emitter.setRate(10);
		break;
	case 'a':
		a = true;
		break;
	case 's':
		s = true;
		break;
	case 'd':
		d = true;
		break;
	case OF_KEY_ALT:
		cam.enableMouseInput();
		bAltKeyDown = true;
		break;
	case OF_KEY_CONTROL:
		bCtrlKeyDown = true;
		break;
	case OF_KEY_SHIFT:
		break;
	case OF_KEY_DEL:
		break;
	case OF_KEY_LEFT:   // turn left
		left = true;
		break;
	case OF_KEY_RIGHT:  // turn right
		right = true;
		break;
	case OF_KEY_UP: // go forward
		forward = true;
		break;
	case OF_KEY_DOWN:   // go backward
		backward = true;
		break;
	default:
		break;
	}
}

void ofApp::toggleWireframeMode() {
	bWireframe = !bWireframe;
}

void ofApp::toggleSelectTerrain() {
	bTerrainSelected = !bTerrainSelected;
}

void ofApp::togglePointsDisplay() {
	bDisplayPoints = !bDisplayPoints;
}



void ofApp::keyReleased(int key) {

	switch (key) {

	case OF_KEY_ALT:
		cam.disableMouseInput();
		bAltKeyDown = false;
		break;
	case OF_KEY_CONTROL:
		bCtrlKeyDown = false;
		break;
	case OF_KEY_SHIFT:
		break;
	case OF_KEY_LEFT:   // turn left
		left = false;
		break;
	case OF_KEY_RIGHT:  // turn right
		right = false;
		break;
	case OF_KEY_UP:     // go forward
		forward = false;
		break;
	case OF_KEY_DOWN:   // go backward
		backward = false;
		break;
	case 'w':
		w = false;
		emitter.setRate(0);
		break;
	case 'a':
		a = false;
		break;
	case 's':
		s = false;
		break;
	case 'd':
		d = false;
		break;
	default:
		break;

	}
}



//--------------------------------------------------------------
void ofApp::mouseMoved(int x, int y) {


}


//--------------------------------------------------------------
void ofApp::mousePressed(int x, int y, int button) {
	// if moving camera, don't allow mouse interaction
	//
	if (cam.getMouseInputEnabled()) return;

	// if rover is loaded, test for selection
	//
	if (bLanderLoaded) {
		glm::vec3 origin = cam.getPosition();
		glm::vec3 mouseWorld = cam.screenToWorld(glm::vec3(mouseX, mouseY, 0));
		glm::vec3 mouseDir = glm::normalize(mouseWorld - origin);

		ofVec3f min = lander.getSceneMin() + lander.getPosition();
		ofVec3f max = lander.getSceneMax() + lander.getPosition();

		Box bounds = Box(Vector3(min.x, min.y, min.z), Vector3(max.x, max.y, max.z));
		bool hit = bounds.intersect(Ray(Vector3(origin.x, origin.y, origin.z), Vector3(mouseDir.x, mouseDir.y, mouseDir.z)), 0, 10000);
		if (hit) {
			bLanderSelected = true;
			mouseDownPos = getMousePointOnPlane(lander.getPosition(), cam.getZAxis());
			mouseLastPos = mouseDownPos;
			bInDrag = true;
		}
		else {
			bLanderSelected = false;
		}
	}
	else {
		ofVec3f p;
		//raySelectWithOctree(p);
	}
}

bool ofApp::raySelectWithOctree(ofVec3f &pointRet) {
	ofVec3f mouse(mouseX, mouseY);
	ofVec3f rayPoint = cam.screenToWorld(mouse);
	ofVec3f rayDir = rayPoint - cam.getPosition();
	rayDir.normalize();
	Ray ray = Ray(Vector3(rayPoint.x, rayPoint.y, rayPoint.z),
		Vector3(rayDir.x, rayDir.y, rayDir.z));

	pointSelected = octree.intersect(ray, octree.root, selectedNode);
	if (pointSelected) {
		pointRet = octree.mesh.getVertex(selectedNode.points[0]);
	}
	return pointSelected;
}


float ofApp::findAlt(ofVec3f &pointRet) {
	ofVec3f position = ofVec3f(lander.getPosition());
	Vector3 pos = Vector3(position.x, position.y, position.z);

	Ray ray = Ray(pos, Vector3(0, 1, 0));

	pointSelected = octree.intersect(ray, octree.root, selectedNode);

	if (pointSelected) {
		pointRet = octree.mesh.getVertex(selectedNode.points[0]);
	}
	return position.y - pointRet.y;
}


//--------------------------------------------------------------
void ofApp::mouseDragged(int x, int y, int button) {

	// if moving camera, don't allow mouse interaction
	//
	if (cam.getMouseInputEnabled()) return;

	if (bInDrag) {

		glm::vec3 landerPos = lander.getPosition();

		glm::vec3 mousePos = getMousePointOnPlane(landerPos, cam.getZAxis());
		glm::vec3 delta = mousePos - mouseLastPos;

		landerPos += delta;
		lander.setPosition(landerPos.x, landerPos.y, landerPos.z);
		mouseLastPos = mousePos;

		ofVec3f min = lander.getSceneMin() + lander.getPosition();
		ofVec3f max = lander.getSceneMax() + lander.getPosition();

		Box bounds = Box(Vector3(min.x, min.y, min.z), Vector3(max.x, max.y, max.z));

		colBoxList.clear();
		octree.intersect(bounds, octree.root, colBoxList);


		if (bounds.overlap(testBox)) {
			cout << "overlap" << endl;
		}
		else {
			cout << "OK" << endl;
		}
	}
	else {
		ofVec3f p;
		//raySelectWithOctree(p);
	}


}

//--------------------------------------------------------------
void ofApp::mouseReleased(int x, int y, int button) {
	bInDrag = false;
}



// Set the camera to use the selected point as it's new target
//  
void ofApp::setCameraTarget() {

}


//--------------------------------------------------------------
void ofApp::mouseEntered(int x, int y) {

}

//--------------------------------------------------------------
void ofApp::mouseExited(int x, int y) {

}

//--------------------------------------------------------------
void ofApp::windowResized(int w, int h) {

}

//--------------------------------------------------------------
void ofApp::gotMessage(ofMessage msg) {

}



//--------------------------------------------------------------
// setup basic ambient lighting in GL  (for now, enable just 1 light)
//
void ofApp::initLightingAndMaterials() {

	static float ambient[] =
	{ .5f, .5f, .5, 1.0f };
	static float diffuse[] =
	{ 1.0f, 1.0f, 1.0f, 1.0f };

	static float position[] =
	{ 5.0, 5.0, 5.0, 0.0 };

	static float lmodel_ambient[] =
	{ 1.0f, 1.0f, 1.0f, 1.0f };

	static float lmodel_twoside[] =
	{ GL_TRUE };


	glLightfv(GL_LIGHT0, GL_AMBIENT, ambient);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse);
	glLightfv(GL_LIGHT0, GL_POSITION, position);

	glLightfv(GL_LIGHT1, GL_AMBIENT, ambient);
	glLightfv(GL_LIGHT1, GL_DIFFUSE, diffuse);
	glLightfv(GL_LIGHT1, GL_POSITION, position);


	glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
	glLightModelfv(GL_LIGHT_MODEL_TWO_SIDE, lmodel_twoside);

	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	//	glEnable(GL_LIGHT1);
	glShadeModel(GL_SMOOTH);
}

void ofApp::savePicture() {
	ofImage picture;
	picture.grabScreen(0, 0, ofGetWidth(), ofGetHeight());
	picture.save("screenshot.png");
	cout << "picture saved" << endl;
}

//--------------------------------------------------------------
//
// support drag-and-drop of model (.obj) file loading.  when
// model is dropped in viewport, place origin under cursor
//
void ofApp::dragEvent2(ofDragInfo dragInfo) {

	ofVec3f point;
	mouseIntersectPlane(ofVec3f(0, 0, 0), cam.getZAxis(), point);
	if (lander.loadModel(dragInfo.files[0])) {
		lander.setScaleNormalization(false);
		//		lander.setScale(.1, .1, .1);
			//	lander.setPosition(point.x, point.y, point.z);
		lander.setPosition(1, 1, 0);

		bLanderLoaded = true;
		for (int i = 0; i < lander.getMeshCount(); i++) {
			bboxList.push_back(Octree::meshBounds(lander.getMesh(i)));
		}

		cout << "Mesh Count: " << lander.getMeshCount() << endl;
	}
	else cout << "Error: Can't load model" << dragInfo.files[0] << endl;
}

bool ofApp::mouseIntersectPlane(ofVec3f planePoint, ofVec3f planeNorm, ofVec3f &point) {
	ofVec2f mouse(mouseX, mouseY);
	ofVec3f rayPoint = cam.screenToWorld(glm::vec3(mouseX, mouseY, 0));
	ofVec3f rayDir = rayPoint - cam.getPosition();
	rayDir.normalize();
	return (rayIntersectPlane(rayPoint, rayDir, planePoint, planeNorm, point));
}

//--------------------------------------------------------------
//
// support drag-and-drop of model (.obj) file loading.  when
// model is dropped in viewport, place origin under cursor
//
void ofApp::dragEvent(ofDragInfo dragInfo) {
	if (lander.loadModel(dragInfo.files[0])) {
		bLanderLoaded = true;
		lander.setScaleNormalization(false);
		lander.setPosition(0, 0, 0);
		cout << "number of meshes: " << lander.getNumMeshes() << endl;
		bboxList.clear();
		for (int i = 0; i < lander.getMeshCount(); i++) {
			bboxList.push_back(Octree::meshBounds(lander.getMesh(i)));
		}

		//		lander.setRotation(1, 180, 1, 0, 0);

				// We want to drag and drop a 3D object in space so that the model appears 
				// under the mouse pointer where you drop it !
				//
				// Our strategy: intersect a plane parallel to the camera plane where the mouse drops the model
				// once we find the point of intersection, we can position the lander/lander
				// at that location.
				//

				// Setup our rays
				//
		glm::vec3 origin = cam.getPosition();
		glm::vec3 camAxis = cam.getZAxis();
		glm::vec3 mouseWorld = cam.screenToWorld(glm::vec3(mouseX, mouseY, 0));
		glm::vec3 mouseDir = glm::normalize(mouseWorld - origin);
		float distance;

		bool hit = glm::intersectRayPlane(origin, mouseDir, glm::vec3(0, 0, 0), camAxis, distance);
		if (hit) {
			// find the point of intersection on the plane using the distance 
			// We use the parameteric line or vector representation of a line to compute
			//
			// p' = p + s * dir;
			//
			glm::vec3 intersectPoint = origin + distance * mouseDir;

			// Now position the lander's origin at that intersection point
			//
			glm::vec3 min = lander.getSceneMin();
			glm::vec3 max = lander.getSceneMax();
			float offset = (max.y - min.y) / 2.0;
			lander.setPosition(intersectPoint.x, intersectPoint.y - offset, intersectPoint.z);

			// set up bounding box for lander while we are at it
			//
			landerBounds = Box(Vector3(min.x, min.y, min.z), Vector3(max.x, max.y, max.z));
		}
	}


}

//  intersect the mouse ray with the plane normal to the camera 
//  return intersection point.   (package code above into function)
//
glm::vec3 ofApp::getMousePointOnPlane(glm::vec3 planePt, glm::vec3 planeNorm) {
	// Setup our rays
	//
	glm::vec3 origin = cam.getPosition();
	glm::vec3 camAxis = cam.getZAxis();
	glm::vec3 mouseWorld = cam.screenToWorld(glm::vec3(mouseX, mouseY, 0));
	glm::vec3 mouseDir = glm::normalize(mouseWorld - origin);
	float distance;

	bool hit = glm::intersectRayPlane(origin, mouseDir, planePt, planeNorm, distance);

	if (hit) {
		// find the point of intersection on the plane using the distance 
		// We use the parameteric line or vector representation of a line to compute
		//
		// p' = p + s * dir;
		//
		glm::vec3 intersectPoint = origin + distance * mouseDir;

		return intersectPoint;
	}
	else return glm::vec3(0, 0, 0);
}
