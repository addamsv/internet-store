import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Loading from './Loading';

export default function App() {
  return (
    <Loading />
  );
}
/* 
export default function App() {
  return (
    <View style={styles.container}>
      <View style={styles.containerUp}>
        <Text style={styles.text}>Open up App.js to start working on your app!!!</Text>
      </View>
      <View style={styles.containerDown}>
        <Text style={styles.text}>Open up App.js to start working on your app!!!</Text>
      </View>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    // backgroundColor: '#f00',
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  containerUp: {
    flex: 1,
    backgroundColor: '#fff',
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  containerDown: {
    flex: 3,
    backgroundColor: '#ccc',
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  text: {
    color: '#aaa',
    fontSize: 24,
    width: '80%',
  },
}); */
