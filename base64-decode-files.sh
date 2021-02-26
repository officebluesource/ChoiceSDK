#!/usr/bin/env bash
echo $KEYSTORE_BASE64 | base64 -d > keystore
echo $HMS_PROPERTIES | base64 -d > ChoiceSDK/choicesdk-app/hms.properties
echo $GOOGLE_SERVICES_JSON | base64 -d > ChoiceSDK/choicesdk-app/google-services.json
echo $AGCONNECT_SERVICES_JSON | base64 -d > ChoiceSDK/choicesdk-app/agconnect-services.json