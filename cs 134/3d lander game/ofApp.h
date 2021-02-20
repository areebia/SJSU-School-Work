#pragma once

#include "ofMain.h"
#include "ofxGui.h"
#include  "ofxAssimpModelLoader.h"
#include <glm/gtx/intersect.hpp>
#include "Octree.h"
#include "Particle.h"
#include "ParticleEmitter.h"




class ofApp : public ofBaseApp{

	public:
		void setup();
		void update();
		void draw();
		void checkCollisions();
		void keyPressed(int key);
		void keyReleased(int key);
		void mouseMoved(int x, int y );
		void mouseDragged(int x, int y, int button);
		void mousePressed(int x, int y, int button);
		void mouseReleased(int x, int y, int button);
		void mouseEntered(int x, int y);
		void mouseExited(int x, int y);
		void windowResized(int w, int h);
		void dragEvent2(ofDragInfo dragInfo);
		void dragEvent(ofDragInfo dragInfo);
		void gotMessage(ofMessage msg);
		void drawAxis(ofVec3f);
		void initLightingAndMaterials();
		void savePicture();
		void toggleWireframeMode();
		void togglePointsDisplay();
		void toggleSelectTerrain();
		void setCameraTarget();
		bool mouseIntersectPlane(ofVec3f planePoint, ofVec3f planeNorm, ofVec3f &point);
		bool raySelectWithOctree(ofVec3f &pointRet);
		float findAlt(ofVec3f &pointRet);


		glm::vec3 ofApp::getMousePointOnPlane(glm::vec3 p , glm::vec3 n);

		ofEasyCam cam;
		ofxAssimpModelLoader moon, lander;
		ofLight light;
		Box boundingBox, landerBounds;
		Box testBox;
		vector<Box> colBoxList;
		bool bLanderSelected = false;
		Octree octree;
		TreeNode selectedNode;
		glm::vec3 mouseDownPos, mouseLastPos;
		bool bInDrag = false;


		ofxIntSlider numLevels;
		ofxPanel gui;

		bool bAltKeyDown;
		bool bCtrlKeyDown;
		bool bWireframe;
		bool bDisplayPoints;
		bool bPointSelected;
		bool bHide;
		bool pointSelected = false;
		bool bDisplayLeafNodes = false;
		bool bDisplayOctree = false;
		bool bDisplayBBoxes = false;
		
		bool bLanderLoaded;
		bool bTerrainSelected;
	
		ofVec3f selectedPoint;
		ofVec3f intersectPoint;

		vector<Box> bboxList;

		const float selectionRange = 4.0;


		//Areeb code portion
		//images
		ofImage bg;

		//particle setup
		Particle player;
		ParticleSystem sys;

		//integrator variables
		ofVec3f position;
		float rot;
		ofVec3f heading;
		ofVec3f sideh;
		
		ofVec3f vel;
		ofVec3f accel;
		ofVec3f grav;
		float damping;
		int rvel;
		int raccel;
		float restitution = .85;

		bool forward;
		bool backward;
		bool left;
		bool right;
		bool w;
		bool a;
		bool s;
		bool d;

		bool space;
		bool moving;

		//thrust emitter system
		ParticleEmitter emitter;

		ThrusterForce *thrusterForce;
		GravityForce *gravityForce;
		CyclicForce *cyclicForce;

		//Test forces 
		TurbulenceForce *turbForce;
		ImpulseRadialForce *radialForce;
		
		
		
};
